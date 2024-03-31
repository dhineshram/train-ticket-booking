package com.cloudbees.trainbooking.controller;

import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.model.train.TrainResponse;
import com.cloudbees.trainbooking.service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainController {

    private final TrainService service;

    public TrainController(TrainService service) {
        this.service = service;
    }

    @GetMapping("/train/{id}")
    @Operation(summary = "API for - #5",
            description = "5. The user is allocated a seat in the train. Assume the train has only 2 sections, section A and section B.")
    public ResponseEntity<TrainResponse> getTrain(@PathVariable int id) throws NotFoundException {
        var train = service.getTrain(id);
        return new ResponseEntity<>(train, HttpStatus.OK);
    }

}
