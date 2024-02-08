package com.spring.mvc.services;

import com.spring.mvc.dto.EventDto;
import com.spring.mvc.models.Event;

import java.util.List;

public interface EventService {
    void createEvent(Integer clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findByEventId(Integer eventId);

    void updateEvent(EventDto eventDto);

    void deleteEvent(Integer eventId);
}
