package ru.digitalhabbits.homework3.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.digitalhabbits.homework3.dao.DepartmentDao;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DepartmentServiceImpl.class)
class DepartmentServiceTest {

    @MockBean
    private DepartmentDao departmentDao;

    @Autowired
    private DepartmentService departmentService;

    @Test
    void findAllDepartments() {
        // TODO: NotImplemented
    }

    @Test
    void getDepartment() {
        // TODO: NotImplemented
    }

    @Test
    void createDepartment() {
        // TODO: NotImplemented
    }

    @Test
    void updateDepartment() {
        // TODO: NotImplemented
    }

    @Test
    void deleteDepartment() {
        // TODO: NotImplemented
    }

    @Test
    void addPersonToDepartment() {
        // TODO: NotImplemented
    }

    @Test
    void removePersonToDepartment() {
        // TODO: NotImplemented
    }

    @Test
    void closeDepartment() {
        // TODO: NotImplemented
    }
}