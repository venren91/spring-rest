package net.venren.api.integeration;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.venren.Main;
import net.venren.api.UserController;
import net.venren.model.User;
import net.venren.service.UserRepository;
import net.venren.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    UserController userController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.userController).build();// Standalone context
    }


    @Test
    public void userRegistrationSuccessful() throws Exception{
        User user = new User("testUser","test@mail.com");
        mockMvc.perform(post("/users/register")
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }
}
