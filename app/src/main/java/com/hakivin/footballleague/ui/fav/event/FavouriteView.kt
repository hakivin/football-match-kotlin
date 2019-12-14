package com.hakivin.footballleague.ui.fav.event

import com.hakivin.footballleague.model.EventItem

interface FavouriteView {
    fun getFavEvents(events: List<EventItem>)
}