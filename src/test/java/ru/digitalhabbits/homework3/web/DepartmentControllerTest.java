package ru.digitalhabbits.homework3.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.digitalhabbits.homework3.service.DepartmentService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DepartmentController.class)
class DepartmentControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void departments() {
        // TODO: NotImplemented
    }

    @Test
    void department() {
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