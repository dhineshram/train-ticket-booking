package com.cloudbees.trainbooking.model.schedule;

import com.cloudbees.trainbooking.entity.Schedule;
import com.cloudbees.trainbooking.entity.Train;
import com.cloudbees.trainbooking.model.train.TrainResponse;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleResponse {

    private int id;
    private TrainResponse trainDetails;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime startTime;
    private int ticketFare;
    private int availableSeats;

    public ScheduleResponse() {}

    public ScheduleResponse(Schedule schedule) {
        id = schedule.getId();
        date = schedule.getDate();
        startTime = schedule.getStartTime();
        ticketFare = schedule.getTicketFare();
        availableSeats = schedule.getAvailableSeats();
    }

}
