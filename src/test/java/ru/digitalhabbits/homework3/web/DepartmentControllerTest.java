package ru.digitalhabbits.homework3.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.digitalhabbits.homework3.domain.Department;
import ru.digitalhabbits.homework3.model.DepartmentRequest;
import ru.digitalhabbits.homework3.model.DepartmentResponse;
import ru.digitalhabbits.homework3.model.DepartmentShortResponse;
import ru.digitalhabbits.homework3.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DepartmentController.class)
class DepartmentControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void departments() throws Exception{
        // TODO: NotImplemented
        List<DepartmentShortResponse> departmentShortResponseList = new ArrayList<>();
        DepartmentShortResponse departmentShortResponse1 = new DepartmentShortResponse().setId(1).setName("Department_1");
        DepartmentShortResponse departmentShortResponse2 = new DepartmentShortResponse().setId(2).setName("Department_2");
        departmentShortResponseList.add(departmentShortResponse1);
        departmentShortResponseList.add(departmentShortResponse2);

        given(this.departmentService.findAllDepartments()).willReturn(departmentShortResponseList);

        this.mockMvc.perform(get("/api/v1/departments").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:1, name:Department_1}, {id:2, name:Department_2}]"));
    }

    @Test
    void department() throws Exception{
        // TODO: NotImplemented
        DepartmentResponse departmentResponse = new DepartmentResponse().setId(1).setName("Department_1").setClosed(false);

        given(this.departmentService.getDepartment(anyInt())).willReturn(departmentResponse);

        this.mockMvc.perform(get("/api/v1/departments/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:1, name:Department_1, closed: false}"));
    }

    @Test
    void createDepartment() throws Exception{
        // TODO: NotImplemented
        Integer createdId = 1;

        given(this.departmentService.createDepartment(any(DepartmentRequest.class))).willReturn(createdId);

        this.mockMvc.perform(post("/api/v1/departments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New_name\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateDepartment() throws Exception{
        // TODO: NotImplemented
        DepartmentResponse departmentResponse = new DepartmentResponse().setId(1).setName("New_name").setClosed(false);

        given(this.departmentService.updateDepartment(anyInt(), any(DepartmentRequest.class))).willReturn(departmentResponse);

        this.mockMvc.perform(patch("/api/v1/departments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New_name\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:1, name:New_name, closed: false}"));
    }

    @Test
    void deleteDepartment() throws Exception{
        // TODO: NotImplemented
        this.mockMvc.perform(delete("/api/v1/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(departmentService, times(1)).deleteDepartment(anyInt());
    }

    @Test
    void addPersonToDepartment() throws Exception{
        // TODO: NotImplemented
        this.mockMvc.perform(post("/api/v1/departments/1/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(departmentService, times(1)).addPersonToDepartment(anyInt(), anyInt());
    }

    @Test
    void removePersonToDepartment() throws Exception{
        // TODO: NotImplemented
        this.mockMvc.perform(delete("/api/v1/departments/1/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(departmentService, times(1)).removePersonToDepartment(anyInt(), anyInt());
    }

    @Test
    void closeDepartment() throws Exception{
        // TODO: NotImplemented
        this.mockMvc.perform(post("/api/v1/departments/1/close")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(departmentService, times(1)).closeDepartment(anyInt());
    }
}