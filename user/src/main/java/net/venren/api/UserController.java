package net.venren.api;

import net.venren.model.User;
import net.venren.resources.PageResource;
import net.venren.resources.UserResource;
import net.venren.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody User user){
        User registeredUser = userService.registerUser(user);
        UserResource userResource = new UserResource(registeredUser);
        userResource.add(ControllerLinkBuilder.linkTo(
                methodOn(UserController.class).getUserById(String.valueOf(user.getId()))).withSelfRel());
        return new ResponseEntity(userResource,HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable final String userId){
        return userService.findUserById(userId).map(u -> new ResponseEntity<User>(u,HttpStatus.FOUND)).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public  PageResource<User> listUsers(Pageable pageable){
        Page<User> users = userService.listUsers(pageable);
        return new PageResource<User>(users,"page","size");
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable final String userId){
        user.setId(Long.valueOf(userId));
        User updatedUser = userService.updateUser(user);
        if(null == updatedUser)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable final String userId){
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
