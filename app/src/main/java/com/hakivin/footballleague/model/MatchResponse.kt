package com.hakivin.footballleague.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(@SerializedName("event")
                         val events: List<EventItem>)