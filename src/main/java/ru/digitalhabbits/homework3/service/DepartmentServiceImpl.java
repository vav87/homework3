package ru.digitalhabbits.homework3.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import ru.digitalhabbits.homework3.dao.DepartmentDao;
import ru.digitalhabbits.homework3.dao.PersonDao;
import ru.digitalhabbits.homework3.domain.Department;
import ru.digitalhabbits.homework3.domain.Person;
import ru.digitalhabbits.homework3.model.*;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl
        implements DepartmentService {
    private final DepartmentDao departmentDao;
    private final PersonDao personDao;

    @Nonnull
    @Override
    public List<DepartmentShortResponse> findAllDepartments() {
        // TODO: NotImplemented: получение краткой информации о всех департаментах
        //throw new NotImplementedException();
        List<DepartmentShortResponse> result = departmentDao.findAll()
                .stream()
                .map(d -> new DepartmentShortResponse().setId(d.getId()).setName(d.getName()))
                .collect(Collectors.toList());
        return result;
    }

    @Nonnull
    @Override
    public DepartmentResponse getDepartment(@Nonnull Integer id) {
        // TODO: NotImplemented: получение подробной информации о департаменте и краткой информации о людях в нем.
        //  Если не найдено, отдавать 404:NotFound
        //throw new NotImplementedException();
        DepartmentResponse result = new DepartmentResponse();
        try {
            Department d = departmentDao.findById(id);
            result.setId(d.getId())
                    .setName(d.getName())
                    .setClosed(d.isClosed())
                    .setPersons(getPersonInfos(d));
        } catch (NullPointerException npe) {
            throw new EntityNotFoundException("404:NotFound");
        }
        return result;
    }

    @Nonnull
    @Override
    @Transactional
    public Integer createDepartment(@Nonnull DepartmentRequest request) {
        // TODO: NotImplemented: создание нового департамента
        // throw new NotImplementedException();
        Department newDepartment = new Department().setName(request.getName());
        Department createdDepartment = departmentDao.update(newDepartment);
        return createdDepartment.getId();
    }

    @Nonnull
    @Override
    @Transactional
    public DepartmentResponse updateDepartment(@Nonnull Integer id, @Nonnull DepartmentRequest request) {
        // TODO: NotImplemented: обновление данных о департаменте. Если не найдено, отдавать 404:NotFound
        //throw new NotImplementedException();
        Department departmentToUpdate = departmentDao.findById(id);
        try {
            departmentToUpdate.setName(request.getName());

        } catch (NullPointerException npe) {
            throw new EntityNotFoundException("404:NotFound");
        }
        Department updatedDepartment = departmentDao.update(departmentToUpdate);
        DepartmentResponse result = new DepartmentResponse()
                .setId(updatedDepartment.getId())
                .setName(updatedDepartment.getName())
                .setClosed(updatedDepartment.isClosed())
                .setPersons(getPersonInfos(updatedDepartment));
        return result;
    }

    @Override
    @Transactional
    public void deleteDepartment(@Nonnull Integer id) {
        // TODO: NotImplemented: удаление всех людей из департамента и удаление самого департамента.
        //  Если не найдено, то ничего не делать
        //throw new NotImplementedException();
        Department departmentToDelete = departmentDao.findById(id);
        if(departmentToDelete != null) {
            Set<Person> persons = departmentToDelete.getPersons();
            for (Person p: persons) {
                p.setDepartment(null);
            }
            departmentToDelete.getPersons().clear();
            departmentDao.delete(id);
        }
    }

    @Override
    @Transactional
    public void addPersonToDepartment(@Nonnull Integer departmentId, @Nonnull Integer personId) {
        // TODO: NotImplemented: добавление нового человека в департамент.
        //  Если не найден человек или департамент, отдавать 404:NotFound.
        //  Если департамент закрыт, то отдавать 409:Conflict
        //throw new NotImplementedException();
        Department departmentToUpdate = departmentDao.findById(departmentId);
        Person personToAdd = personDao.findById(personId);
        try {
            if (departmentToUpdate.isClosed()) {
               throw new ConflictException("409:Conflict");
            }
            departmentToUpdate.getPersons().add(personToAdd);
            personToAdd.setDepartment(departmentToUpdate);
        } catch (NullPointerException npe) {
            throw new EntityNotFoundException("404:NotFound");
        }
        departmentDao.update(departmentToUpdate);
    }

    @Override
    @Transactional
    public void removePersonToDepartment(@Nonnull Integer departmentId, @Nonnull Integer personId) {
        // TODO: NotImplemented: удаление человека из департамента.
        //  Если департамент не найден, отдавать 404:NotFound, если не найден человек в департаменте, то ничего не делать
        //throw new NotImplementedException();
        Department departmentToUpdate = departmentDao.findById(departmentId);
        Person personToRemove = personDao.findById(personId);
        try {
            Set<Person> persons = departmentToUpdate.getPersons();
            if(persons.contains(personToRemove)) {
                personToRemove.setDepartment(null);
                persons.remove(personToRemove);
            }
        } catch (NullPointerException npe) {
            throw new EntityNotFoundException("404:NotFound");
        }
        departmentDao.update(departmentToUpdate);
    }

    @Override
    @Transactional
    public void closeDepartment(@Nonnull Integer id) {
        // TODO: NotImplemented: удаление всех людей из департамента и установка отметки на департаменте,
        //  что он закрыт для добавления новых людей. Если не найдено, отдавать 404:NotFound
        //throw new NotImplementedException();
        Department departmentToClose = departmentDao.findById(id);
        try {
            Set<Person> persons = departmentToClose.getPersons();
            for (Person p: persons) {
                p.setDepartment(null);
            }
            departmentToClose.getPersons().clear();
            departmentToClose.setClosed(true);
        } catch (NullPointerException npe) {
            throw new EntityNotFoundException("404:NotFound");
        }
        departmentDao.update(departmentToClose);
    }

    private List<PersonInfo> getPersonInfos(Department department) {
        return department.getPersons()
                .stream()
                .map(p -> new PersonInfo().setId(p.getId()).setFullName(getFullName(p)))
                .collect(Collectors.toList());
    }

    private String getFullName(Person person) {
        String fullName = person.getFirstName().concat(" "+person.getLastName());
        if(person.getMiddleName() != null) {
            fullName = fullName.concat(" "+person.getMiddleName());
        }
        return fullName;
    }
}
