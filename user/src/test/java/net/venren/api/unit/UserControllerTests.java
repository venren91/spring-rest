package net.venren.api.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.venren.api.UserController;
import net.venren.model.User;
import net.venren.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;



@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void userRegistrationSuccessful() throws Exception{
        User user = new User("testUser","test@mail.com");
        given(userService.registerUser(user)).willReturn(user);
        mockMvc.perform(post("/users/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void userRegistrationWithInvalidRequestBody() throws Exception{
        mockMvc.perform(post("/users/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString("random body")))
                .andExpect(status().isBadRequest());
    }
}
