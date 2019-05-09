package net.venren.api;

import net.venren.model.User;
import net.venren.resources.PageResource;
import net.venren.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody User user){
        return new ResponseEntity(userService.registerUser(user),HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable final String userId){
        return userService.retrieveUser(userId).map(u -> new ResponseEntity<User>(u,HttpStatus.FOUND)).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public  PageResource<User> listUsers(Pageable pageable){
        Page<User> users = userService.listUsers(pageable);
        return new PageResource<User>(users,"page","size");
    }


}
