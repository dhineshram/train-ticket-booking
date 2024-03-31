package com.cloudbees.trainbooking.service.impl;

import com.cloudbees.trainbooking.entity.User;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.repository.UserRepository;
import com.cloudbees.trainbooking.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found with id: " + id));
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}
