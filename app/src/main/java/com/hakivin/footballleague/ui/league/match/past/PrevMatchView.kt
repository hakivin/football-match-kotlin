package com.hakivin.footballleague.ui.league.match.past

import com.hakivin.footballleague.model.EventItem

interface PrevMatchView {
    fun showPastEvents(list: List<EventItem>?)
    fun showNextEvents(list: List<EventItem>?)
    fun showSearchedEvents(list: List<EventItem>?)
}