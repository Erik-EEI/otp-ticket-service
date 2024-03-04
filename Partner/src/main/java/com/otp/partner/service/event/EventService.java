package com.otp.partner.service.event;

import com.otp.partner.entity.Event;
import com.otp.partner.exception.EventNotFoundException;
import com.otp.partner.repository.EventRepository;
import com.otp.partner.interfaces.EventServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements EventServiceInterface {

    private final EventRepository eventRepository;
    private final Logger LOGGER = LoggerFactory.getLogger("[EVENT SERVICE]");

    @Autowired
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    /**
     * Retrieves all events from the database.
     * @return A list of all events.
     */
    @Override
    public List<Event> getAllEvents() {
        LOGGER.info("► \uD83D\uDCC2 - Getting all events from database");
        return eventRepository.findAll();
    }

    /**
     * Retrieves an event by its ID from the database.
     * @param eventId The ID of the event to retrieve.
     * @return The event with the specified ID.
     * @throws EventNotFoundException If the event with the specified ID is not found.
     */
    @Override
    public Event getEventById(Long eventId) {
        LOGGER.info(String.format("► \uD83D\uDCC2 - Getting event with ID %s from database",eventId));
        return eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
    }
}
