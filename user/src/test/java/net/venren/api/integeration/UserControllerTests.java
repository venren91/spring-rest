package net.venren.api.integeration;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.venren.model.User;
import net.venren.service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUsers_whenGetUsers_thenStatus200()throws Exception{
        userRepository.save(new User("venren","venren@mail.com"));
        userRepository.save(new User("xxx","xxxx@mail.com"));
        userRepository.save(new User("yyy","yyyy@mail.com"));

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/hal+json;charset=UTF-8"));
    }

    @Test
    public void duplicateEmail_registerUser_thenStatus409()throws Exception{

        User duplicateUser = new User("zzz","zzzz@mail.com");
        userRepository.save(duplicateUser);
        userRepository.save(duplicateUser);

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(duplicateUser)))
                .andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/hal+json;charset=UTF-8"));
    }

}
