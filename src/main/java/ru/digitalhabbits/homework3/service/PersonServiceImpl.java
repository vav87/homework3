package ru.digitalhabbits.homework3.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import ru.digitalhabbits.homework3.dao.PersonDao;
import ru.digitalhabbits.homework3.domain.Department;
import ru.digitalhabbits.homework3.domain.Person;
import ru.digitalhabbits.homework3.model.DepartmentInfo;
import ru.digitalhabbits.homework3.model.PersonRequest;
import ru.digitalhabbits.homework3.model.PersonResponse;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl
        implements PersonService {
    private final PersonDao personDao;

    @Nonnull
    @Override
    public List<PersonResponse> findAllPersons() {
        // TODO: NotImplemented: получение информации о всех людях во всех отделах
        //throw new NotImplementedException();
        List<PersonResponse> result = personDao.findAll()
                .stream()
                .map(p -> new PersonResponse()
                        .setId(p.getId())
                        .setAge(p.getAge())
                        .setFullName(getFullName(p))
                        .setDepartment(getDepartmentInfo(p.getDepartment())))
                .collect(Collectors.toList());
        return result;
    }

    @Nonnull
    @Override
    public PersonResponse getPerson(@Nonnull Integer id) {
        // TODO: NotImplemented: получение информации о человеке. Если не найдено, отдавать 404:NotFound
        //throw new NotImplementedException();
        Person person = personDao.findById(id);
        try {
            return new PersonResponse()
                    .setId(person.getId())
                    .setFullName(getFullName(person))
                    .setAge(person.getAge())
                    .setDepartment(getDepartmentInfo(person.getDepartment()));
        } catch(NullPointerException npe) {
            throw new EntityNotFoundException("404:NotFound");
        }
    }

    @Nonnull
    @Override
    @Transactional
    public Integer createPerson(@Nonnull PersonRequest request) {
        // TODO: NotImplemented: создание новой записи о человеке
        //throw new NotImplementedException();
        Person person = new Person()
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setMiddleName(request.getMiddleName())
                .setAge(request.getAge());
        Person createdPerson = personDao.update(person);
        return createdPerson.getId();
    }

    @Nonnull
    @Override
    @Transactional
    public PersonResponse updatePerson(@Nonnull Integer id, @Nonnull PersonRequest request) {
        // TODO: NotImplemented: обновление информации о человеке. Если не найдено, отдавать 404:NotFound
        //throw new NotImplementedException();
        Person personToUpdate = personDao.findById(id);
        try {
            personToUpdate
                    .setFirstName(request.getFirstName() !=null ? request.getFirstName() : personToUpdate.getFirstName())
                    .setLastName(request.getLastName() !=null ? request.getLastName() : personToUpdate.getLastName())
                    .setMiddleName(request.getMiddleName() !=null ? request.getMiddleName() : personToUpdate.getMiddleName())
                    .setAge(request.getAge() !=null ? request.getAge() : personToUpdate.getAge());
        } catch(NullPointerException npe) {
            throw new EntityNotFoundException("404:NotFound");
        }
        Person updatedPerson = personDao.update(personToUpdate);
        return new PersonResponse()
                .setId(updatedPerson.getId())
                .setFullName(getFullName(updatedPerson))
                .setAge(updatedPerson.getAge())
                .setDepartment(getDepartmentInfo(updatedPerson.getDepartment()));
    }

    @Override
    @Transactional
    public void deletePerson(@Nonnull Integer id) {
        // TODO: NotImplemented: удаление информации о человеке и удаление его из отдела. Если не найдено, ничего не делать
        //throw new NotImplementedException();
        Person personToDelete = personDao.findById(id);
        if(personToDelete != null) {
            personDao.delete(id);
        }
    }

    private String getFullName(Person person) {
        String fullName = person.getFirstName().concat(" "+person.getLastName());
        if(person.getMiddleName() != null) {
            fullName = fullName.concat(" "+person.getMiddleName());
        }
        return fullName;
    }

    private DepartmentInfo getDepartmentInfo(Department department) {
        if (department == null) {
            return null;
        }
        Integer dId = department.getId();
        String dN = department.getName();
        return new DepartmentInfo().setId(dId).setName(dN);
    }
}
