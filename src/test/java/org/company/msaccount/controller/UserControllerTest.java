package org.company.msaccount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.company.msaccount.enums.ActiveStatus;
import org.company.msaccount.mapper.UserMapper;
import org.company.msaccount.model.UserRequest;
import org.company.msaccount.model.UserResponse;
import org.company.msaccount.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Test
    void usersSave() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("test");
        userRequest.setEmail("test@test.com");
        userRequest.setPassword("test");
        userRequest.setSurname("test");
        Mockito.when(userService.saveUser(Mockito.any(UserRequest.class))).thenReturn(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("1"));

    }

    @Test
    void getUser() throws Exception {
        UserResponse userResponse1 = new UserResponse();
        userResponse1.setName("test");
        userResponse1.setEmail("test@test.com");
        userResponse1.setSurname("test");
        Mockito.when(userService.findUserById(1L)).thenReturn(userResponse1);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name").value("test"))
                .andExpect((ResultMatcher) jsonPath("$.surname").value("test"))
                .andExpect((ResultMatcher) jsonPath("$.email").value("test@test.com"));

    }

    @Test
    void updateUserPartially() throws Exception {
        Mockito.doNothing().when(userService).updateUserPartially(Mockito.eq(1L),Mockito.anyString(),Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.patch("/v1/users/1")
                .param("email","nihad").param("password","nihad"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteUserById(Mockito.eq(1L),Mockito.eq(ActiveStatus.DEACTIVE));
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/users/1")).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void updateUser() throws Exception {
        UserRequest userRequest=new UserRequest();
        userRequest.setName("test");
        userRequest.setEmail("test@test.com");
        userRequest.setPassword("test");
        userRequest.setSurname("test");
        Mockito.doNothing().when(userService).updateUser(Mockito.eq(1L),Mockito.any(UserRequest.class));
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/users/1").contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());


    }
}