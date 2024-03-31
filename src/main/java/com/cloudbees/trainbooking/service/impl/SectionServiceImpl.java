package com.cloudbees.trainbooking.service.impl;

import com.cloudbees.trainbooking.entity.Section;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.repository.SectionRepository;
import com.cloudbees.trainbooking.service.SectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository repository;

    public SectionServiceImpl(SectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Section> findAllBy(int trainNumber) {
        return repository.findAllByTrainNumber(trainNumber);
    }

    @Override
    public Section create(Section section) {
        return repository.save(section);
    }

    @Override
    public Section get(int id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No section found with id: " + id));
    }

    @Override
    public List<Section> getAll() {
        return repository.findAll();
    }
}
