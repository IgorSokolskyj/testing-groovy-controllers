package com.riversoft.test.controller

import com.riversoft.test.model.Bet
import com.riversoft.test.service.BetService
import groovy.util.logging.Slf4j
import jdk.nashorn.internal.ir.annotations.Ignore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RequestMapping('/api/bet')
class BetController {


    @Autowired private BetService betService

    @PostMapping
    List<Bet> addBet(@RequestBody Bet bet) {
        log.info("received bet : eventId - ${bet.eventId}, amount - ${bet.amount} ")
        return betService.addBet(bet)
    }


    @GetMapping
    List<Bet> getBet(@Ignore String eventId) {

        if (eventId) {
            log.info("give to client event by id ${eventId}")
            return betService.searchBetById(eventId)
        } else {
            log.info("give to client all events")
            return betService.getAllBets()
        }
    }


    @PutMapping("/{eventId}")
    Bet updateAmount (@RequestBody  Bet bet ,@PathVariable String eventId,@RequestHeader(value="ver-api") int verApi) throws  Exception {
        if (verApi >= 10) {
            log.info("received update name by id : ${eventId}")
            return betService.updateAmount(eventId, bet.amount)
        } else {
            new Bet(eventId:'',amount:"Oppps something went wrong you have error 500")
        }
    }


    @DeleteMapping
    Bet deleteBet(@RequestParam String eventId) {
        log.info("received event id to delete bet with it id")
        return betService.deleteBet(eventId)
    }
}
