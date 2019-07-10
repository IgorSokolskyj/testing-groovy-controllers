package com.riversoft.test.controller

import com.riversoft.test.model.Event
import com.riversoft.test.service.EventService
import groovy.util.logging.Slf4j
import jdk.nashorn.internal.ir.annotations.Ignore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController




@Slf4j
@RestController
@RequestMapping("/api/event")
class EventController {

    @Autowired private EventService eventService


    @PostMapping
    List<Event> addEvent(@RequestBody Event event)  {
        log.info("received event : id - ${event.eventId}, name - ${event.eventName}")
        return eventService.addEvent(event)
    }


    @GetMapping
    List<Event> getEvents ( @Ignore String eventId) {

        if (eventId) {
            log.info("give to client event by id ${eventId}")
            return eventService.searchEventById(eventId)
        } else {
            log.info("give to client all events")
           return  eventService.getAllEvents()
        }
    }


    @PutMapping("{eventId}")
    Event updateEvent(@RequestBody Event event, @PathVariable String eventId) {
        log.info("received update name by id : ${eventId}")
        return eventService.updateEventName(eventId,event.eventName)
    }


    @DeleteMapping
    Event  deleteEvent(@RequestParam String eventId) {
        log.info("received event id to delete this event")
        return eventService.deleteEvent(eventId)
    }
}
