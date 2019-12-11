package com.hakivin.footballleague.ui.event

import com.google.gson.Gson
import com.hakivin.footballleague.model.EventResponse
import com.hakivin.footballleague.model.TeamResponse
import com.hakivin.footballleague.remote.Api
import com.hakivin.footballleague.remote.TheSportDBApi
import com.hakivin.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EventPresenter(private val view: EventView,
                     private val api: Api,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getEvent(idEvent : Int?){
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(api.doRequest(TheSportDBApi.getMatchDetail(idEvent)).await(), EventResponse::class.java)
            view.showEvent(data.events)
        }
    }

    fun getHomeTeam(idHomeTeam: Int?){
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(api.doRequest(TheSportDBApi.getTeam(idHomeTeam)).await(), TeamResponse::class.java)
            view.showHomeTeam(data.team)
        }
    }

    fun getAwayTeam(idAwayTeam: Int?){
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(api.doRequest(TheSportDBApi.getTeam(idAwayTeam)).await(), TeamResponse::class.java)
            view.showAwayTeam(data.team)
        }
    }
}