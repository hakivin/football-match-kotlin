package com.hakivin.footballleague.model

import com.google.gson.annotations.SerializedName

data class TeamItem(@SerializedName("idTeam")
                    val id: String?,
                    @SerializedName("strTeam")
                    val name: String?,
                    @SerializedName("strTeamBadge")
                    val logo: String?)