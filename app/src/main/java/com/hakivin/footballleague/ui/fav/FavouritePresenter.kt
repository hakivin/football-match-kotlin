package com.hakivin.footballleague.ui.fav

import com.hakivin.footballleague.db.DatabaseHelper
import com.hakivin.footballleague.model.EventItem
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FavouritePresenter(private val view: FavouriteView) {

    fun getFavEvents(db : DatabaseHelper){
        doAsync {
            var fav : List<EventItem>? = null
            db.use {
                val result = select(EventItem.TABLE_EVENT)
                fav = result.parseList(classParser())
            }
            uiThread {
                fav?.let { it1 -> view.getFavEvents(it1) }
            }
        }
    }
}