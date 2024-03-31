package com.cloudbees.trainbooking.service.impl;

import com.cloudbees.trainbooking.entity.Booking;
import com.cloudbees.trainbooking.entity.BookingView;
import com.cloudbees.trainbooking.entity.Schedule;
import com.cloudbees.trainbooking.entity.Section;
import com.cloudbees.trainbooking.exception.BadRequestException;
import com.cloudbees.trainbooking.exception.CustomException;
import com.cloudbees.trainbooking.exception.DuplicateException;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.model.booking.CreateBookingRequest;
import com.cloudbees.trainbooking.model.booking.UpdateBookingRequest;
import com.cloudbees.trainbooking.repository.BookingRepository;
import com.cloudbees.trainbooking.service.BookingViewService;
import com.cloudbees.trainbooking.service.ScheduleService;
import com.cloudbees.trainbooking.service.SectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {

    @InjectMocks
    BookingServiceImpl service;

    @Mock
    BookingRepository repository;
    @Mock
    SectionService sectionService;
    @Mock
    ScheduleService scheduleService;
    @Mock
    BookingViewService bookingViewService;

    @BeforeEach
    void beforeEach() throws NotFoundException {
        when(repository.findByScheduleIdAndSectionIdAndSeatNumber(anyInt(), anyInt(), anyInt()))
            .thenReturn(Optional.empty());
        when(repository.save(any(Booking.class)))
                .thenReturn(Booking.builder().id(bookingId).build());
        when(repository.findById(anyInt()))
                .thenReturn(Optional.of(Booking.builder().id(bookingId).build()));
        when(sectionService.get(anyInt()))
                .thenReturn(Section.builder().id(sectionId).name("A").totalSeats(10).build());
        when(scheduleService.get(anyInt()))
                .thenReturn(Schedule.builder().id(scheduleId).availableSeats(10).build());
        when(scheduleService.update(any(Schedule.class)))
                .thenReturn(null);
        when(bookingViewService.get(anyInt()))
                .thenReturn(BookingView.builder().bookingId(bookingId).build());
    }

    int bookingId = 1, scheduleId = 2, userId = 3, sectionId = 4, seatNumber = 5;

    @Nested
    class CreateBooking {

        @Test
        void allGood() throws CustomException {

            var request = buildRequest();
            var actual = service.create(request);

            verify(sectionService).get(eq(sectionId));
            verify(repository).findByScheduleIdAndSectionIdAndSeatNumber(eq(scheduleId), eq(sectionId), eq(seatNumber));
            verify(scheduleService).get(eq(scheduleId));
            verify(scheduleService).update(any(Schedule.class));

            assertEquals(bookingId, actual.getBookingId());
        }

        @Test
        void seatAlreadyTaken() {

            when(repository.findByScheduleIdAndSectionIdAndSeatNumber(anyInt(), anyInt(), anyInt()))
                    .thenReturn(Optional.of(Booking.builder().id(bookingId).build()));

            var request = buildRequest();
            assertThrows(DuplicateException.class, () -> service.create(request));
        }

        @Test
        void invalidSeatNumber() {

            var request = buildRequest();
            request.setSeatNumber(2000);

            assertThrows(BadRequestException.class, () -> service.create(request));
        }

        @Test
        void allSeatsAreTaken() throws NotFoundException {

            var schedule = Schedule.builder().id(scheduleId).availableSeats(0).build();
            when(scheduleService.get(anyInt()))
                    .thenReturn(schedule);

            var request = buildRequest();
            assertThrows(BadRequestException.class, () -> service.create(request));
        }

        CreateBookingRequest buildRequest() {
            return CreateBookingRequest.builder()
                    .userId(userId)
                    .scheduleId(scheduleId)
                    .sectionId(sectionId)
                    .seatNumber(seatNumber)
                    .build();
        }

    }

    @Test
    void updateBookingAllGood() throws CustomException {
        var request = UpdateBookingRequest.builder()
                .seatNumber(seatNumber)
                .sectionId(sectionId)
                .build();
        var actual = service.update(bookingId, request);

        verify(repository).findById(eq(bookingId));
        verify(sectionService).get(eq(request.getSectionId()));
        verify(repository).findByScheduleIdAndSectionIdAndSeatNumber(anyInt(), anyInt(), anyInt());
        verify(repository).save(any(Booking.class));
        verify(bookingViewService).get(eq(bookingId));

        assertEquals(bookingId, actual.getBookingId());
    }

}