package com.scrumapp.service;

import com.scrumapp.model.User;
import com.scrumapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving users.", e);
        }
    }

    public Optional<User> getUserById (int id) {
        try {
            return userRepository.findById(Long.valueOf(id));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving donation details.", e);
        }
    }

    public void updateUser(User user, int Id) {
        user.setId(Id);
        userRepository.save(user);
    }

    public boolean deleteUser(int Id) {
        try {
            userRepository.deleteById(Long.valueOf(Id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}