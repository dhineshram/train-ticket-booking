package com.cloudbees.trainbooking.repository;

import com.cloudbees.trainbooking.entity.BookingView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingViewRepository extends ReadOnlyRepository<BookingView, Integer> {

    List<BookingView> findAllByTrainNumberAndSectionName(int trainNumber, String sectionName);

}
