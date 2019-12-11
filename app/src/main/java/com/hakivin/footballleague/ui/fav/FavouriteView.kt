package com.hakivin.footballleague.ui.fav

import com.hakivin.footballleague.model.EventItem

interface FavouriteView {
    fun getFavEvents(events: List<EventItem>)
}