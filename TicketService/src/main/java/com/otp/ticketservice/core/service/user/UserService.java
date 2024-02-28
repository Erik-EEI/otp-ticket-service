package com.otp.ticketservice.core.service.user;

import com.otp.ticketservice.core.entity.User;
import com.otp.ticketservice.core.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long userId){
        return userRepository.findById( userId )
                .orElseThrow(RuntimeException::new); //TODO Replace with Custom exception
    }
}
