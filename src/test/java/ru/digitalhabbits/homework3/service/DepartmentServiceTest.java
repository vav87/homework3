package ru.digitalhabbits.homework3.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.digitalhabbits.homework3.dao.DepartmentDao;
import ru.digitalhabbits.homework3.dao.PersonDao;
import ru.digitalhabbits.homework3.domain.Department;
import ru.digitalhabbits.homework3.domain.Person;
import ru.digitalhabbits.homework3.model.DepartmentRequest;
import ru.digitalhabbits.homework3.model.DepartmentResponse;
import ru.digitalhabbits.homework3.model.DepartmentShortResponse;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DepartmentServiceImpl.class)
class DepartmentServiceTest {

    @MockBean
    private DepartmentDao departmentDao;

    @MockBean
    private PersonDao personDao;

    @Autowired
    private DepartmentService departmentService;

    @Test
    void findAllDepartments() {
        // TODO: NotImplemented
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(new Department().setId(1).setName("D1").setClosed(false));
        departmentList.add(new Department().setId(2).setName("D2").setClosed(false));

        given(this.departmentDao.findAll()).willReturn(departmentList);

        List<DepartmentShortResponse> departmentShortResponseList = departmentService.findAllDepartments();

        assertThat(departmentShortResponseList.size()).isEqualTo(departmentList.size());
    }

    @Test
    void getDepartment() {
        // TODO: NotImplemented
        Department department = new Department().setId(1).setName("D1").setClosed(false);
        given(this.departmentDao.findById(anyInt())).willReturn(department);

        DepartmentResponse departmentResponse = departmentService.getDepartment(1);

        assertThat(departmentResponse).isEqualToComparingOnlyGivenFields(department, "id", "name", "closed");
    }

    @Test
    void createDepartment() {
        // TODO: NotImplemented
        Department department = new Department().setId(1).setName("D1").setClosed(false);
        given(this.departmentDao.update(any(Department.class))).willReturn(department);

        DepartmentRequest departmentRequest = new DepartmentRequest().setName("D1");
        Integer integer = departmentService.createDepartment(departmentRequest);

        assertThat(integer).isEqualTo(department.getId());
    }

    @Test
    void updateDepartment() {
        // TODO: NotImplemented
        Department department = new Department().setId(1).setName("D1").setClosed(false);
        given(this.departmentDao.findById(anyInt())).willReturn(department);
        given(this.departmentDao.update(any(Department.class))).willReturn(department);

        DepartmentRequest departmentRequest = new DepartmentRequest().setName("D1");
        DepartmentResponse departmentResponse = departmentService.updateDepartment(1, departmentRequest);

        assertThat(departmentResponse).isEqualToComparingOnlyGivenFields(department, "id", "name", "closed");
    }

    @Test
    void deleteDepartment() {
        // TODO: NotImplemented
        Department department = new Department().setId(1).setName("D1").setClosed(false);
        given(this.departmentDao.findById(anyInt())).willReturn(department);

        departmentService.deleteDepartment(1);
        verify(departmentDao, times(1)).findById(anyInt());
        verify(departmentDao, times(1)).delete(anyInt());
    }

    @Test
    void addPersonToDepartment() {
        // TODO: NotImplemented
        Department department = new Department().setId(1).setName("D1").setClosed(false);
        Person person = new Person().setId(1).setFirstName("A").setLastName("B").setAge(30);
        given(this.departmentDao.findById(anyInt())).willReturn(department);
        given(this.personDao.findById(anyInt())).willReturn(person);

        departmentService.addPersonToDepartment(1, 1);
        verify(departmentDao, times(1)).findById(anyInt());
        verify(personDao, times(1)).findById(anyInt());
        verify(departmentDao, times(1)).update(any(Department.class));
    }

    @Test
    void removePersonToDepartment() {
        // TODO: NotImplemented
        Department department = new Department().setId(1).setName("D1").setClosed(false);
        Person person = new Person().setId(1).setFirstName("A").setLastName("B").setAge(30);
        given(this.departmentDao.findById(anyInt())).willReturn(department);
        given(this.personDao.findById(anyInt())).willReturn(person);

        departmentService.removePersonToDepartment(1, 1);
        verify(departmentDao, times(1)).findById(anyInt());
        verify(personDao, times(1)).findById(anyInt());
        verify(departmentDao, times(1)).update(any(Department.class));
    }

    @Test
    void closeDepartment() {
        // TODO: NotImplemented
        Department department = new Department().setId(1).setName("D1").setClosed(false);
        given(this.departmentDao.findById(anyInt())).willReturn(department);
        departmentService.closeDepartment(1);
        verify(departmentDao, times(1)).findById(anyInt());
        verify(departmentDao, times(1)).update(any(Department.class));
    }
}