package net.venren.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import net.venren.model.User;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

    private User user;

    @JsonCreator
    public UserResource(User user){
        this.user = user;
    }


    public User getUser() {
        return user;
    }
}
