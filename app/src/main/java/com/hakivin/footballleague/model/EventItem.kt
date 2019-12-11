package com.hakivin.footballleague.model

import com.google.gson.annotations.SerializedName

data class EventItem(@SerializedName("idEvent")
                     val id: String?,
                     @SerializedName("strHomeTeam")
                     val home: String?,
                     @SerializedName("strAwayTeam")
                     val away: String?,
                     @SerializedName("intHomeScore")
                     val homeScore: String?,
                     @SerializedName("intAwayScore")
                     val awayScore: String?,
                     @SerializedName("dateEvent")
                     val date: String?,
                     @SerializedName("idHomeTeam")
                     val idHome: String?,
                     @SerializedName("idAwayTeam")
                     val idAway: String?,
                     @SerializedName("strHomeGoalDetails")
                     val goalHome: String?,
                     @SerializedName("strAwayGoalDetails")
                     val goalAway: String?,
                     @SerializedName("strHomeLineupGoalkeeper")
                     val gkHome: String?,
                     @SerializedName("strAwayLineupGoalkeeper")
                     val gkAway: String?,
                     @SerializedName("strHomeLineupDefense")
                     val defHome: String?,
                     @SerializedName("strAwayLineupDefense")
                     val defAway: String?,
                     @SerializedName("strHomeLineupMidfield")
                     val midHome: String?,
                     @SerializedName("strAwayLineupMidfield")
                     val midAway: String?,
                     @SerializedName("strHomeLineupForward")
                     val fwdHome: String?,
                     @SerializedName("strAwayLineupForward")
                     val fwdAway: String?,
                     @SerializedName("strHomeLineupSubstitutes")
                     val subHome: String?,
                     @SerializedName("strAwayLineupSubstitutes")
                     val subAway: String?,
                     @SerializedName("strSport")
                     val type: String?){

    companion object {
        const val TABLE_EVENT: String = "TABLE_EVENT"
        const val ID_EVENT: String = "ID_EVENT"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val ID_TEAM_HOME: String = "ID_TEAM_HOME"
        const val ID_TEAM_AWAY: String = "ID_TEAM_AWAY"
        const val GOAL_HOME: String = "GOAL_HOME"
        const val GOAL_AWAY: String = "GOAL_AWAY"
        const val GK_HOME: String = "GK_HOME"
        const val GK_AWAY: String = "GK_AWAY"
        const val DEF_HOME: String = "DEF_HOME"
        const val DEF_AWAY: String = "DEF_AWAY"
        const val MID_HOME: String = "MID_HOME"
        const val MID_AWAY: String = "MID_AWAY"
        const val FWD_HOME: String = "FWD_HOME"
        const val FWD_AWAY: String = "FWD_AWAY"
        const val SUB_HOME: String = "SUB_HOME"
        const val SUB_AWAY: String = "SUB_AWAY"
        const val TYPE_EVENT: String = "TYPE_EVENT"
    }
}