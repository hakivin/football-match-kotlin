package com.hakivin.footballleague.ui.league.teams

import com.google.gson.Gson
import com.hakivin.footballleague.model.TeamResponse
import com.hakivin.footballleague.remote.Api
import com.hakivin.footballleague.remote.TheSportDBApi
import com.hakivin.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter (val api: Api,
                     val gson: Gson,
                     val view: TeamView,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeams(idLeague: Int?){
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(api.doRequest(TheSportDBApi.getAllTeams(idLeague)).await(), TeamResponse::class.java)
            view.showTeams(data.team)
        }
    }
}