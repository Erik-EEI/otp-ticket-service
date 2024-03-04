package com.otp.partner.interfaces;

import com.otp.partner.entity.Event;

import java.util.List;

public interface EventServiceInterface {
    List<Event> getAllEvents();
    Event getEventById(Long eventId);
}
