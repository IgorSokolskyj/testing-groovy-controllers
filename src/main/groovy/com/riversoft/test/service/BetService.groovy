package com.riversoft.test.service

import com.riversoft.test.model.Bet
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class BetService {

    List<Bet> bets = [] // initialization List to save bets

    //add bet from users

    List<Bet> addBet(Bet bet) {
        bets.add(bet)
        def allBets = bets.findAll{it.eventId}
        log.info("save bet : eventId - ${bet.eventId}, amount ${bet.amount}")
        return allBets
    }

    // get all bets from List bets

    List<Bet> getAllBets () {
        def allBets = bets.findAll{it.amount}
        return allBets
    }

    // get bet by id

    List<Bet> searchBetById (String eventId) {
        def foundBet =  bets.findAll{it.eventId == eventId}
        return foundBet
    }

    // update amount by id

    Bet updateAmount(String eventId, String amount) {
        def foundBetById = bets.find {it.eventId == eventId}
        foundBetById.amount = amount
        log.info( "change event name for : ${foundBetById.eventId}")
        return foundBetById
    }

    // delete bet by id

    Bet deleteBet (String eventId) {
        def betToDelete = bets.find{it.eventId == eventId}
        bets.remove(betToDelete)
        log.info("Delete bet : id - ${betToDelete.eventId}")
        return betToDelete
    }
}
