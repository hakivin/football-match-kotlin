package com.hakivin.footballleague.ui.event

import com.hakivin.footballleague.model.EventItem
import com.hakivin.footballleague.model.TeamItem

interface EventView {
    fun showEvent(events: List<EventItem>?)

    fun showHomeTeam(team: List<TeamItem>?)

    fun showAwayTeam(team: List<TeamItem>?)
}