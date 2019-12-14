package com.hakivin.footballleague.ui.team

import com.hakivin.footballleague.model.TeamItem

interface TeamDetailView {
    fun showTeam(team : List<TeamItem>?)
}