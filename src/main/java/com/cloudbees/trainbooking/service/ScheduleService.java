package com.cloudbees.trainbooking.service;

import com.cloudbees.trainbooking.entity.Schedule;
import com.cloudbees.trainbooking.model.schedule.ScheduleResponse;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService extends GenericService<Schedule> {
    List<ScheduleResponse> findSchedule(String from, String to);

    Schedule update(Schedule schedule);
}
