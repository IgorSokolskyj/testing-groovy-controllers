package com.riversoft.test.service

import com.riversoft.test.model.Event
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service


@Slf4j
@Service
class EventService {

    /**
     * initialization List to save events
     */

    List<Event> events = []


    /**
     * add event to object
     * @param event
     */

     void addEvent (Event event) {

        log.info("receive from user event   : id - ${event.eventId}, name - ${event.eventName}")

        events.add(event)

        log.info("save event : id - ${event.eventId}, name - ${event.eventName}")

    }

    /**
     * get all events from List events
     * @return - all events
     */

    List<Event> getAllEvents () {

        def allEvents = events.findAll()

        log.info("give user all events")

        return allEvents
    }

    /**
     * get event by id
     * @param eventId
     * @return - event by found id
     */

    List<Event> searchEventById (String eventId) {

       log.info("receive from user event id : ${eventId}")

       def foundEvent =  events.findAll{it.eventId == eventId}

       if (!foundEvent) { throw  new IllegalStateException("Not found event by id: ${eventId}") }

       log.info("give user event  by  id : ${eventId}")

       return foundEvent
    }


    /**
     * update event name by id
     * @param eventId
     * @param eventName
     * @return - success message
     */


    Event updateEventName(String eventId,String eventName) {

        log.info("receive from user event id : ${eventId} new event name value ${eventName}")

        def foundEventById = events.find {it.eventId == eventId}

        if (!foundEventById) { throw new IllegalStateException("Not found event by id :${eventId}") }

        foundEventById.eventName = eventName

        log.info( "change event name for : ${foundEventById.eventId}")

        return foundEventById
    }


    /**
     * delete event by id
     * @param eventId
     * @return  - success message
     */

    void deleteEvent (String eventId) {

        log.info("receive from user event to delete by id : ${eventId}")

        def eventToDelete = events.find{it.eventId == eventId}

        if(!eventToDelete){ throw new IllegalStateException("Not found event to delete by id : ${eventId}") }

        events.remove(eventToDelete)

        log.info("Delete event : id - ${eventToDelete.eventId}")

    }
}

