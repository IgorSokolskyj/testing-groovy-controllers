package com.riversoft.test.controller

import com.riversoft.test.model.Bet
import com.riversoft.test.service.BetService
import groovy.util.logging.Slf4j
import io.swagger.annotations.ApiOperation
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
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.swagger2.annotations.EnableSwagger2

import javax.validation.Valid

@Slf4j
@RestController
@RequestMapping('/api/bet')
class BetController {


    @Autowired private BetService betService


    @PostMapping
    String addBet(@RequestBody @Valid Bet bet) {

        log.info("received bet : eventId - ${bet.eventId}, amount - ${bet.amount}")

        betService.addBet(bet)

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Save bet : ${bet.eventId} ${bet.amount}")
    }


    @ApiOperation(value = "Get an employee by Id")
    @GetMapping
    List<Bet> getBet(@RequestParam(required = false) String eventId) {

        if (eventId) {

            log.info("give to client event by id ${eventId}")

            return betService.searchBetById(eventId)

        } else {

            log.info("Give to client all events")

            return betService.getAllBets()
        }
    }


    @PutMapping("/{eventId}")
    Bet updateAmount (@RequestBody  Bet bet ,@PathVariable String eventId,@RequestHeader(value="ver-api") int verApi) {


        if (verApi < 10) {

          throw new IllegalStateException("Wrton")

        } else {

            log.info("received update name by id : ${eventId}")

            return betService.updateAmount(eventId, bet.amount)

        }
    }


    @DeleteMapping
    String deleteBet(@RequestParam String eventId) {

        log.info("received event id to delete bet with it id")

        betService.deleteBet(eventId)

        return ResponseEntity.status(HttpStatus.OK).body("Delete is successful")
    }

}
