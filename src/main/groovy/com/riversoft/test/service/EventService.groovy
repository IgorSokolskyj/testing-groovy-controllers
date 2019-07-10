package com.riversoft.test.service

import com.riversoft.test.model.Event
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service


@Slf4j
@Service
class EventService {

    List<Event> events = [] // initialization List to save events

    // add event to object

     List<Event> addEvent (Event event) {
        events.add(event)
        def allEvents = events.each{it.eventName}
        log.info("save event : id - ${event.eventId}, name - ${event.eventName}")
        log.info("All events : ${allEvents.eventName}")
        return allEvents
    }

    // get all events from List events

    List<Event> getAllEvents () {
        def allEvents = events.findAll{it.eventName}
        return allEvents
    }

    // get event by id

    List<Event> searchEventById (String eventId) {
       def foundEvent =  events.findAll{it.eventId == eventId}
       return foundEvent
    }

    // update event name by id

    Event updateEventName(String eventId,String eventName) {
        def foundEventById = events.find {it.eventId == eventId}
        foundEventById.eventName = eventName
        log.info( "change event name for : ${foundEventById.eventId}")
        return foundEventById
    }

    // Delete event by id

    Event deleteEvent (String eventId) {
        def eventToDelete = events.find{it.eventId == eventId}
        events.remove(eventToDelete)
        log.info("Delete event : id - ${eventToDelete.eventId}")
        return eventToDelete
    }
}

