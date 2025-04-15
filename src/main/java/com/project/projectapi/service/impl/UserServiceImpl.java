package com.project.projectapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.projectapi.dto.UserKafkaMessage;
import com.project.projectapi.entities.User;
import com.project.projectapi.enums.ActionType;
import com.project.projectapi.exceptions.InvalidEmailFormat;
import com.project.projectapi.exceptions.InvalidPasswordFormat;
import com.project.projectapi.exceptions.NullEntry;
import com.project.projectapi.exceptions.PasswordTooShort;
import com.project.projectapi.exceptions.UserAlreadyExist;
import com.project.projectapi.exceptions.UserNotFound;
import com.project.projectapi.kafka.KafkaProducerService;
import com.project.projectapi.repository.UserRepository;
import com.project.projectapi.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
     private final UserRepository userRepository;
    private final KafkaProducerService kafkaProducerService;

    public UserServiceImpl(UserRepository userRepository, KafkaProducerService kafkaProducerService) {
        this.userRepository = userRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExist(user.getEmail() + "already exists");
        }
        if (user.getPassword().length() < 8) {
            throw new PasswordTooShort("Pasword should be at least eight characters");
        }
        else if (!isValidPassword(user.getPassword())) {
            throw new InvalidPasswordFormat("Password must consist of an Uppercase,Lowercase, Number and Special Character");
        }
        if (user.getEmail().isBlank()) {
            throw new NullEntry("Input your Email");
        }
        else if (!isValidEmail(user.getEmail())) {
            throw new InvalidEmailFormat("Invalid email format");
        }
        if (user.getFirstName().isBlank()) {
            throw new NullEntry("Input your first name");
        }
        if (user.getLastName().isBlank()) {
            throw new NullEntry("Input your last name");
        }
        if (user.getUserName().isBlank()) {
            throw new NullEntry("Input your username");
        }
        if (user.getPhoneNumber().isBlank()) {
            throw new NullEntry("Input your phone number");
        }

        User savedUser = userRepository.save(user);
        sendKafkaMessage(savedUser, ActionType.CREATED);
        return userRepository.save(savedUser);
    }
    private boolean isValidPassword(String password){
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%.^&+=!]).{8,}$";
        return password != null && password.matches(passwordRegex);
    }

    private boolean isValidEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }
    private void sendKafkaMessage(User user, ActionType actionType) {
    UserKafkaMessage message = new UserKafkaMessage();
    message.setId(user.getId());
    message.setUserName(user.getUserName()); // Assuming userName not username
    message.setEmail(user.getEmail());
    message.setPhoneNumber(user.getPhoneNumber());
    message.setActionType(actionType);

    kafkaProducerService.sendMessage(message);
}


    @Override
    public void deleteUser(String email) {
       if (!userRepository.existsByEmail(email)) {
            throw new UserNotFound(email + " not found");
       }
       Optional<User> userOptional = userRepository.findByEmail(email);
       if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
            sendKafkaMessage(user, ActionType.DELETED);
       }
        
    }

    @Override
    public List<User> getAllUsers() {
         return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserbyEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFound(email + " doesn't exist ");
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public User updateUser(String email, User updateUser) {
        User existingUser = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFound(email + " user not found"));

         if (updateUser.getEmail().isBlank()) {
            throw new NullEntry("Input your Email");
        } 
        else if (!isValidEmail(updateUser.getEmail())) {
            throw new InvalidEmailFormat("Invalid email format");
        }  
        else if (!updateUser.getEmail().equals(existingUser.getEmail()) && userRepository.existsByEmail(updateUser.getEmail())) {
            throw new UserAlreadyExist(updateUser.getEmail() + " already exists");
        }
        if (updateUser.getUserName().isBlank()) {
            throw new NullEntry("Input your username");
        }
        if (updateUser.getPhoneNumber().isBlank()) {
            throw new NullEntry("Input your phone number");
        }
        existingUser.setUserName(updateUser.getUserName());
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setPhoneNumber(updateUser.getPhoneNumber());

        User savedUser = userRepository.save(existingUser);
        sendKafkaMessage(savedUser, ActionType.UPDATED);
        return savedUser;
    }

    @Override
    public Page<User> getUsers(String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
    return userRepository.findByEmailContainingIgnoreCase(email, pageable);
    }
}
