package com.hakivin.footballleague.ui.league.match.next

import com.hakivin.footballleague.model.EventItem

interface NextMatchView {
    fun showEvents(list: List<EventItem>?)
}