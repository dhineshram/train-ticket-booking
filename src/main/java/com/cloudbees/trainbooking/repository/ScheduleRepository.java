package com.cloudbees.trainbooking.repository;

import com.cloudbees.trainbooking.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("select e,t,s from Schedule e " +
            "join Train t on e.trainNumber = t.number " +
            "join Section s on s.trainNumber = t.number " +
            "where t.fromStation = ?1 and t.toStation = ?2")
    List<List<Object>> findAllBy(String fromStation, String toStation);

}
