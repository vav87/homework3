package ru.digitalhabbits.homework3.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.digitalhabbits.homework3.service.PersonService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void persons() {
        // TODO: NotImplemented
    }

    @Test
    void person() {
        // TODO: NotImplemented
    }

    @Test
    void createPerson() {
        // TODO: NotImplemented
    }

    @Test
    void updatePerson() {
        // TODO: NotImplemented
    }

    @Test
    void deletePerson() {
        // TODO: NotImplemented
    }
}