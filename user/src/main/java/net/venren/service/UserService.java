package net.venren.service;

import net.venren.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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

    public Optional<User> retrieveUser(String userId){
        return userRepository.findById(Long.valueOf(userId));
    }

    public Page<User> listUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }
}
