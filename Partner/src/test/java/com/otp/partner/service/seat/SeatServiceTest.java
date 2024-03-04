package com.otp.partner.service.seat;

import com.otp.partner.entity.Seat;
import com.otp.partner.exception.SeatNotFoundException;
import com.otp.partner.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    private SeatService seatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.seatService = new SeatService(seatRepository);
    }

    @Test
    void testFindSeatById() {
        long seatId = 1L;
        Seat seat = new Seat();
        seat.setId(seatId);
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));

        Seat retrievedSeat = seatService.findSeatById(seatId);

        assertNotNull(retrievedSeat);
        assertEquals(seatId, retrievedSeat.getId());
        verify(seatRepository, times(1)).findById(seatId);
    }

    @Test
    void testFindSeatByIdNotFound() {
        long seatId = 1L;
        when(seatRepository.findById(seatId)).thenReturn(Optional.empty());

        assertThrows(SeatNotFoundException.class, () -> seatService.findSeatById(seatId));

        verify(seatRepository, times(1)).findById(seatId);
    }

    @Test
    void testUpdateSeatReserved() {
        long seatId = 1L;
        Seat seat = new Seat();
        seat.setId(seatId);
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));

        seatService.updateSeatReserved(seatId, true);

        assertTrue(seat.isReserved());
        verify(seatRepository, times(1)).findById(seatId);
        verify(seatRepository, times(1)).save(seat);
    }

    @Test
    void testUpdateSeatReservedNotFound() {
        long seatId = 1L;
        when(seatRepository.findById(seatId)).thenReturn(Optional.empty());

        assertThrows(SeatNotFoundException.class, () -> seatService.updateSeatReserved(seatId, true));
        
        verify(seatRepository, times(1)).findById(seatId);
        verify(seatRepository, never()).save(any(Seat.class));
    }
}