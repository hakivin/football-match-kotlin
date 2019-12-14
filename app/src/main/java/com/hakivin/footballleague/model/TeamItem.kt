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
                    val banner: String?,
                    @SerializedName("strAlternate")
                    val alternate: String?,
                    @SerializedName("strSport")
                    val type: String?) {
    companion object {
        const val TABLE_TEAM = "TABLE_TEAM"
        const val ID_TEAM = "ID_TEAM"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_LOGO = "TEAM_LOGO"
        const val TEAM_STADIUM = "TEAM_STADIUM"
        const val TEAM_MANAGER = "TEAM_MANAGER"
        const val TEAM_FORMEDYEAR = "TEAM_FORMEDYEAR"
        const val TEAM_DESC = "TEAM_DESC"
        const val TEAM_LOCATION = "TEAM_LOCATION"
        const val TEAM_BANNER = "TEAM_BANNER"
        const val TEAM_ALTERNATE = "TEAM_ALTERNATE"
        const val TEAM_TYPE = "TEAM_TYPE"
    }
}