package com.hakivin.footballleague.ui.league

import com.google.gson.Gson
import com.hakivin.footballleague.model.LeagueResponse
import com.hakivin.footballleague.remote.Api
import com.hakivin.footballleague.remote.TheSportDBApi
import com.hakivin.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeaguePresenter(private val view: LeagueView,
                      private val api: Api,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeague(idLeague: Int?){
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(api.doRequest(TheSportDBApi.getLeague(idLeague)).await(), LeagueResponse::class.java)
            view.showLeague(data.league)
        }
    }
}