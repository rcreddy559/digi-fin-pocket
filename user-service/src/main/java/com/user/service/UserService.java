package com.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.user.dto.UserRequest;
import com.user.entity.User;
import com.user.exception.UserNotFoundException;
import com.user.mapper.UserMapper;
import com.user.repositry.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRequest getUsers(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userMapper.toDto(user.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id)));
    }

    public UserRequest createUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserRequest updateUser(Long id, UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setUserName(userRequest.getUserName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        return userMapper.toDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserRequest getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userMapper.toDto(user.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id)));
    }

    public UserRequest getUserByUserName(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return userMapper
                .toDto(user.orElseThrow(() -> new UserNotFoundException("User not found with userName: " + userName)));
    }

    public UserRequest getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return userMapper
                .toDto(user.orElseThrow(() -> new UserNotFoundException("User not found with email: " + email)));
    }

    public UserRequest getUserByPhoneNumber(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        return userMapper
                .toDto(user.orElseThrow(
                        () -> new UserNotFoundException("User not found with phoneNumber: " + phoneNumber)));
    }

    public UserRequest getUserByRole(String role) {
        Optional<User> user = userRepository.findByRole(role);
        return userMapper
                .toDto(user.orElseThrow(() -> new UserNotFoundException("User not found with role: " + role)));
    }
}
