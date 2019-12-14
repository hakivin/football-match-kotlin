package com.hakivin.footballleague.model

import com.google.gson.annotations.SerializedName

data class SeasonResponse(@SerializedName("seasons") val seasons: List<SeasonItem>?)