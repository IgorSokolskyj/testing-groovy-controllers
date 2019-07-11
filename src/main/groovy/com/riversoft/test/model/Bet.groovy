package com.riversoft.test.model

import javax.validation.constraints.NotNull


class Bet {

    @NotNull
    String eventId

    @NotNull
    String amount
}
