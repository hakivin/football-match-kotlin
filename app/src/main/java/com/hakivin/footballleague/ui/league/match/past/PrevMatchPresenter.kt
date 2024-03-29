package com.hakivin.footballleague.ui.league.match.past

import com.google.gson.Gson
import com.hakivin.footballleague.model.EventItem
import com.hakivin.footballleague.model.EventResponse
import com.hakivin.footballleague.model.MatchResponse
import com.hakivin.footballleague.remote.Api
import com.hakivin.footballleague.remote.TheSportDBApi
import com.hakivin.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrevMatchPresenter(private val view: PrevMatchView,
                         private val api: Api,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getPreviousMatches(idLeague: Int?){
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(api.doRequest(TheSportDBApi.getPreviousMatch(idLeague)).await(), EventResponse::class.java)
            view.showEvents(data.events)
        }
    }

    fun searchMatches(query: String?){
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(api.doRequest(TheSportDBApi.getMatch(query)).await(), MatchResponse::class.java)
            val filtered = arrayListOf<EventItem>()
            for (event in data.events){
                if (event.type.equals("Soccer"))
                    filtered.add(event)
            }
            view.showEvents(filtered)
        }
    }
}