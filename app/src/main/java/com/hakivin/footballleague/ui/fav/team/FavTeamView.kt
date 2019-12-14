package com.hakivin.footballleague.ui.fav.team

import com.hakivin.footballleague.model.TeamItem

interface FavTeamView {
    fun showFavTeams(list: List<TeamItem>?)
}