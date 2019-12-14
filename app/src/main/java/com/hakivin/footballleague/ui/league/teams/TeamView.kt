package com.hakivin.footballleague.ui.league.teams

import com.hakivin.footballleague.model.TeamItem

interface TeamView {
    fun showTeams(list: List<TeamItem>?)
    fun showSearchedTeams(list: List<TeamItem>?)
}