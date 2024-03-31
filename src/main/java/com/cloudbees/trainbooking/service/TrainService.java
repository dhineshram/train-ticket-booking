package com.cloudbees.trainbooking.service;

import com.cloudbees.trainbooking.entity.Train;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.model.train.TrainResponse;

public interface TrainService extends GenericService<Train> {

    TrainResponse getTrain(int number) throws NotFoundException;
}
