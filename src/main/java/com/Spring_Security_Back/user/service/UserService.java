package com.Spring_Security_Back.user.service;

import com.Spring_Security_Back.exceptions.NotFoundException;
import com.Spring_Security_Back.user.interfaces.GetUserData;
import com.Spring_Security_Back.user.models.entity.User;
import com.Spring_Security_Back.user.repository.UserRepository;
import com.Spring_Security_Back.utils.Messages;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements GetUserData {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserEntityByEmail(username);
        if(optionalUser.isEmpty()) {
            throw new NotFoundException(Messages.USER_NOT_FOUND.getMessage());
        }
        return optionalUser.get();
    }
}
