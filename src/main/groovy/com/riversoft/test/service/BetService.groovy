package com.riversoft.test.service

import com.riversoft.test.model.Bet
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class BetService {

    /**
     * initialization List to save bets
     */

   private List<Bet> bets = []


    /**
     * add bet
     * @param bet - model bet
     * @return - model bet which saved to List bets
     */

    void addBet(Bet bet) {

        log.info("receive bet from controller  : eventId - ${bet.eventId}, amount ${bet.amount}")

        bets.add(bet)

        log.info("save bet : eventId - ${bet.eventId}, amount ${bet.amount}")

    }

    /**
     * get all bets from List bets
     * @return - List bets
     */

    List<Bet> getAllBets () {

        def allBets = bets.findAll()

        log.info("give to user all bets")

        return allBets
    }

    /**
     * get bet by event id
     * @param eventId
     * @return - bet with needed event id
     */


    List<Bet> searchBetById (String eventId) {

        log.info("receive from user  event id - ${eventId}")

        def foundBet =  bets.findAll{it.eventId == eventId}

        if (!foundBet) {  throw new IllegalStateException("Not found event by id : ${eventId}")  }

        log.info("give to user bet : event id - ${eventId}")

        return foundBet
    }


    /**
     * update amount by id
     * @param eventId
     * @param amount
     * @return - bet which was updated
     */

    Bet updateAmount(String eventId, String amount) {

        log.info("receive from user event id - ${eventId} new amount value -${amount}")

        def foundBetById = bets.find {it.eventId == eventId}

        if ( !foundBetById) { throw new IllegalStateException("Not found bet  : ${eventId}") }

        foundBetById.amount = amount

        log.info( "change event name for : ${foundBetById.eventId}")

        return foundBetById
    }

    /**
     * delete bet by id
     * @param eventId
     * @return - success msg
     */


    void deleteBet (String eventId) {

        log.info("receive from user id to delete bet : id- ${eventId}")

        def betToDelete = bets.find{it.eventId == eventId}

        if ( !betToDelete) { throw new IllegalStateException("Not found bet  : ${eventId}") }

        bets.remove(betToDelete)

        log.info("Delete bet : id - ${betToDelete.eventId}")

    }
}
