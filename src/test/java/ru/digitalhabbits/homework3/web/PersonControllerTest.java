package ru.digitalhabbits.homework3.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.digitalhabbits.homework3.model.PersonRequest;
import ru.digitalhabbits.homework3.model.PersonResponse;
import ru.digitalhabbits.homework3.service.PersonService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void persons() throws Exception{
        // TODO: NotImplemented
        PersonResponse personResponse1 = new PersonResponse().setId(1).setFullName("A_A_A").setAge(40);
        PersonResponse personResponse2 = new PersonResponse().setId(2).setFullName("B_B_B").setAge(41);
        List<PersonResponse> personResponseList = new ArrayList<>();
        personResponseList.add(personResponse1);
        personResponseList.add(personResponse2);

        given(this.personService.findAllPersons()).willReturn(personResponseList);

        this.mockMvc.perform(get("/api/v1/persons/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:1, fullName:A_A_A, age:40}, {id:2, fullName:B_B_B, age:41}]"));
    }

    @Test
    void person() throws Exception{
        // TODO: NotImplemented
        PersonResponse personResponse = new PersonResponse().setId(1).setFullName("A_B_C").setAge(42);
        given(this.personService.getPerson(anyInt())).willReturn(personResponse);
        this.mockMvc.perform(get("/api/v1/persons/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:1, fullName:A_B_C, age: 42}"));
    }

    @Test
    void createPerson() throws Exception{
        // TODO: NotImplemented
        Integer createdId = 1;
        given(this.personService.createPerson(any(PersonRequest.class))).willReturn(createdId);
        this.mockMvc.perform(post("/api/v1/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"A\", \"lastName\":\"C\", \"age\":\"42\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void updatePerson() throws Exception{
        // TODO: NotImplemented
        PersonResponse personResponse = new PersonResponse().setId(1).setFullName("A_C").setAge(42);
        given(this.personService.updatePerson(anyInt(), any(PersonRequest.class))).willReturn(personResponse);
        this.mockMvc.perform(patch("/api/v1/persons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"A\", \"lastName\":\"C\", \"age\":\"42\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:1, fullName:A_C, age: 42}"));
    }

    @Test
    void deletePerson() throws Exception{
        // TODO: NotImplemented
        this.mockMvc.perform(delete("/api/v1/persons/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(personService, times(1)).deletePerson(anyInt());
    }
}