package com.cloudbees.trainbooking.service.impl;

import com.cloudbees.trainbooking.entity.Train;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.model.section.SectionResponse;
import com.cloudbees.trainbooking.model.train.TrainResponse;
import com.cloudbees.trainbooking.repository.TrainRepository;
import com.cloudbees.trainbooking.service.SectionService;
import com.cloudbees.trainbooking.service.TrainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    private final TrainRepository repository;
    private final SectionService sectionService;

    public TrainServiceImpl(TrainRepository repository, SectionService sectionService) {
        this.repository = repository;
        this.sectionService = sectionService;
    }

    @Override
    public TrainResponse getTrain(int number) throws NotFoundException {
        var train = get(number);
        var sectionList = sectionService.findAllBy(number);
        var sectionRespList = sectionList.stream().map(SectionResponse::new).toList();

        var response = new TrainResponse(train);
        response.setSections(sectionRespList);

        return response;
    }

    @Override
    public Train create(Train train) {
        return repository.save(train);
    }

    @Override
    public Train get(int id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No train found with id: " + id));
    }

    @Override
    public List<Train> getAll() {
        return repository.findAll();
    }
}
