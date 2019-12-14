package com.hakivin.footballleague.ui.league.match

import com.hakivin.footballleague.model.EventItem

interface MatchView {
    fun showPastEvents(list: List<EventItem>?)
    fun showNextEvents(list: List<EventItem>?)
    fun showSearchedEvents(list: List<EventItem>?)
}