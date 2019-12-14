package com.hakivin.footballleague.model

import com.google.gson.annotations.SerializedName

data class TableItem(@SerializedName("name")
                     val teamName: String?,
                     @SerializedName("teamid")
                     val teamId: String?,
                     @SerializedName("played")
                     val played: Int?,
                     @SerializedName("goalsfor")
                     val goalsFor: Int?,
                     @SerializedName("goalsagainst")
                     val goalsAgainst: Int?,
                     @SerializedName("goalsdifference")
                     val goalsDiff: Int?,
                     @SerializedName("win")
                     val win: Int?,
                     @SerializedName("draw")
                     val draw: Int?,
                     @SerializedName("loss")
                     val lose: Int?,
                     @SerializedName("total")
                     val point: Int?)
