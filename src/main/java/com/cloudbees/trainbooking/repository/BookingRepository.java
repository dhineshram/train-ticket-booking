package com.cloudbees.trainbooking.repository;

import com.cloudbees.trainbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Optional<Booking> findByScheduleIdAndSectionIdAndSeatNumber(int scheduleId, int sectionId, int seatNumber);

}
