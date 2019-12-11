package com.hakivin.footballleague.model

import com.google.gson.annotations.SerializedName

data class EventResponse(@SerializedName("events")
                         val events: List<EventItem>)