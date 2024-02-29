package com.otp.partner.mapper;

import com.otp.partner.dto.DetailedEventDTO;
import com.otp.partner.dto.EventDTO;
import com.otp.partner.dto.EventSeatsDTO;
import com.otp.partner.dto.SeatDTO;
import com.otp.partner.entity.Event;
import com.otp.partner.entity.Seat;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class EventMapper {

    public static List<EventDTO> mapToEventDTOList(List<Event> events){
        return events
                .stream()
                .map((event) -> new EventDTO(
                        event.getId(),
                        event.getTitle(),
                        event.getLocation(),
                        event.getStartTimestamp(),
                        event.getEndTimestamp()))
                .toList();
    }

    public static EventSeatsDTO mapToEventSeatsDTO( Event event ){

        List<Seat> seats = event.getSeats();

        return new EventSeatsDTO(
                event.getId(),
                seats.stream()
                        .map(seat -> new SeatDTO(
                                seat.getId(),
                                seat.getSeatName(),
                                seat.getPrice(),
                                seat.getCurrency(),
                                seat.isReserved()
                        ))
                        .toList()
        );
    }

    public static DetailedEventDTO mapToDetailedEventDTO( Event event ){
        List<Seat> seats = event.getSeats();

        List<SeatDTO> seatDTOS = seats.stream()
                .map(seat -> new SeatDTO(
                        seat.getId(),
                        seat.getSeatName(),
                        seat.getPrice(),
                        seat.getCurrency(),
                        seat.isReserved()
                ))
                .toList();

        return new DetailedEventDTO(
                event.getId(),
                event.getTitle(),
                event.getLocation(),
                event.getStartTimestamp(),
                event.getEndTimestamp(),
                seatDTOS
        );
    }
}
