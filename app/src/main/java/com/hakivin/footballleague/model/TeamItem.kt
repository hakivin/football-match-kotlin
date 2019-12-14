package com.hakivin.footballleague.model

import com.google.gson.annotations.SerializedName

data class TeamItem(@SerializedName("idTeam")
                    val id: String?,
                    @SerializedName("strTeam")
                    val name: String?,
                    @SerializedName("strTeamBadge")
                    val logo: String?,
                    @SerializedName("strStadium")
                    val stadium: String?,
                    @SerializedName("strManager")
                    val manager: String?,
                    @SerializedName("intFormedYear")
                    val formedYear: String?,
                    @SerializedName("strDescriptionEN")
                    val desc: String?,
                    @SerializedName("strStadiumLocation")
                    val location: String?,
                    @SerializedName("strTeamLogo")
                    val badge: String?,
                    @SerializedName("strAlternate")
                    val alternate: String?)