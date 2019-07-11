package com.riversoft.test.model

import javax.validation.constraints.NotNull


class Event {

    @NotNull
    String eventId

    @NotNull
    String eventName
}
