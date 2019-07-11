package com.riversoft.test.controller

import com.riversoft.test.model.Event
import com.riversoft.test.service.EventService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid


@Slf4j
@RestController
@RequestMapping("/api/event")
class EventController {

    @Autowired private EventService eventService


    @PostMapping
    String addEvent(@RequestBody @Valid Event event) {

        log.info("received event : id - ${event.eventId}, name - ${event.eventName}")

        eventService.addEvent(event)

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("save event -${event.eventName} ${event.eventId}")
    }


    @GetMapping
    List<Event> getEvents(@RequestParam(required = false) String eventId) {

        if (eventId) {

            log.info("receive to client event by id ${eventId}")

            return eventService.searchEventById(eventId)

        } else {

            log.info("give to client all events")

            return eventService.getAllEvents()
        }
    }


    @PutMapping ("/{eventId}")
    Event updateEvent(@RequestBody Event event, @PathVariable String eventId) {

        log.info("received update name by id : ${eventId}")

        return eventService.updateEventName(eventId, event.eventName)
    }


    @DeleteMapping
    String deleteEvent(@RequestParam String eventId) {

        log.info("received event id to delete this event")

        eventService.deleteEvent(eventId)

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Delete by event id :${eventId} was successful")
    }
}
