package com.cloudbees.trainbooking.service.impl;

import com.cloudbees.trainbooking.entity.BookingView;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.repository.BookingViewRepository;
import com.cloudbees.trainbooking.service.BookingViewService;
import com.cloudbees.trainbooking.service.SectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingViewServiceImpl implements BookingViewService {

    private final BookingViewRepository repository;
    private final SectionService sectionService;

    public BookingViewServiceImpl(BookingViewRepository repository, SectionService sectionService) {
        this.repository = repository;
        this.sectionService = sectionService;
    }

    @Override
    public BookingView get(int bookingId) throws NotFoundException {
        return repository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("No booking view found with bookingId: " + bookingId));
    }

    @Override
    public List<BookingView> findAllWith(int trainNumber, int sectionId) throws NotFoundException {
        var section = sectionService.get(sectionId);
        return repository.findAllByTrainNumberAndSectionName(trainNumber, section.getName());
    }

}
