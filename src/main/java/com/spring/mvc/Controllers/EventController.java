package com.spring.mvc.Controllers;

import com.spring.mvc.dto.ClubDto;
import com.spring.mvc.dto.EventDto;
import com.spring.mvc.models.UserEntity;
import com.spring.mvc.security.SecurityUtil;
import com.spring.mvc.services.EventService;
import com.spring.mvc.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final UserService userService;
    private final SecurityUtil SecurityUtil;

    @GetMapping("/event/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Integer clubId, Model model){
        EventDto eventDto = new EventDto();
        model.addAttribute("clubId",clubId);
        model.addAttribute("event",eventDto);
        return "events-create";

    }

    @PostMapping("/event/{clubId}/new")
    public String createEvent(@PathVariable("clubId") Integer clubId, @ModelAttribute EventDto eventDto){
        eventService.createEvent(clubId,eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/events")
    public String eventsList(Model model){
        UserEntity user = new UserEntity();
        List<EventDto> events = eventService.findAllEvents();
        String username = SecurityUtil.getSessionUser();
        if (username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("events",events);
        return "events-list";
    }

    @GetMapping("/event/{eventId}")
    public String viewEvent(@PathVariable("eventId") Integer eventId,Model model){
        UserEntity user = new UserEntity();
        EventDto eventDto = eventService.findByEventId(eventId);
        String username = SecurityUtil.getSessionUser();
        if (username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("event",eventDto);
        return "events-detail";

    }

    @GetMapping("/event/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Integer eventId,Model model){
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event",eventDto);
        return "events-edit";
    }

    @PostMapping("/event/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Integer eventId,
                             @Valid @ModelAttribute EventDto event,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("event",event);
            if(result.getFieldError("name")!=null){
                model.addAttribute("nameMessage",result.getFieldError("name").getDefaultMessage());
            }
            if(result.getFieldError("photoUrl")!=null){
                model.addAttribute("photoUrlMessage",result.getFieldError("photoUrl").getDefaultMessage());
            }
            if(result.getFieldError("type")!=null){
                model.addAttribute("typeMessage",result.getFieldError("type").getDefaultMessage());
            }

            return "events-edit";
        }
        EventDto eventDto = eventService.findByEventId(eventId);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/event/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Integer eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }

}
