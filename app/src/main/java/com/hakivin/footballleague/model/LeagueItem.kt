package com.hakivin.footballleague.model

import com.google.gson.annotations.SerializedName

data class LeagueItem(@SerializedName("idLeague")
                  val id: String?,
                  @SerializedName("strLeague")
                  val name: String?,
                  @SerializedName("strLogo")
                  val badge: String?,
                  @SerializedName("strDescriptionEN")
                  val description: String?)