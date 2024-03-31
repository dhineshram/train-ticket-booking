package com.cloudbees.trainbooking.service;

import com.cloudbees.trainbooking.exception.NotFoundException;

import java.util.List;

public interface GenericService<T> {

    T create(T user);
    T get(int id) throws NotFoundException;

    List<T> getAll();

}
