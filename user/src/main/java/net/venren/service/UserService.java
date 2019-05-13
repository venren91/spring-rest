package net.venren.service;

import net.venren.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
            this.userRepository = userRepository;
    }

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> findUserById(String userId){
        return userRepository.findById(Long.valueOf(userId));
    }

    public Page<User> listUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(Long.valueOf(userId));
    }

    public User updateUser(@NotNull @Valid final User user){
        User existing = userRepository.getOne(user.getId());
        if(existing == null){
            return null;
        }else{
            return userRepository.save(user);
        }
    }
}
