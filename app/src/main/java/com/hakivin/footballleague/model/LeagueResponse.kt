package com.hakivin.footballleague.model

import com.google.gson.annotations.SerializedName

data class LeagueResponse(@SerializedName("leagues")
                          val league: List<LeagueItem>?)