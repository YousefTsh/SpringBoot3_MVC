package com.spring.mvc.services.implementation;

import com.spring.mvc.dto.EventDto;
import com.spring.mvc.models.Club;
import com.spring.mvc.models.Event;
import com.spring.mvc.repositorys.ClubRepository;
import com.spring.mvc.repositorys.EventRepository;
import com.spring.mvc.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

import static com.spring.mvc.mappers.EventMapper.mapToEvent;
import static com.spring.mvc.mappers.EventMapper.mapToEventDto;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final ClubRepository clubRepository;
    private final EventRepository eventRepository;

    @Override
    public void createEvent(Integer clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);

    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Integer eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);

    }

    @Override
    public void deleteEvent(Integer eventId) {
        eventRepository.deleteById(eventId);
    }


}
