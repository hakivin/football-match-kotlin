package com.hakivin.footballleague.ui.league.match.past

import com.hakivin.footballleague.model.EventItem

interface PrevMatchView {
    fun showEvents(list: List<EventItem>?)
}