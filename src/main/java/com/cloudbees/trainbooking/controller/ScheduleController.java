package com.cloudbees.trainbooking.controller;

import com.cloudbees.trainbooking.entity.Schedule;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.model.schedule.ScheduleResponse;
import com.cloudbees.trainbooking.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping("/schedules/find")
    @Operation(summary = "API for - #1",
            description = "1. I want to board a train from London to France. The train ticket will cost $20")
    public ResponseEntity<List<ScheduleResponse>> findSchedule(@RequestParam String from, @RequestParam String to) {
        var list = service.findSchedule(from, to);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
