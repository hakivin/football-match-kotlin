package com.hakivin.footballleague.ui.team

import com.google.gson.Gson
import com.hakivin.footballleague.model.TeamResponse
import com.hakivin.footballleague.remote.Api
import com.hakivin.footballleague.remote.TheSportDBApi
import com.hakivin.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter (private val api: Api,
                           private val gson: Gson,
                           private val view: TeamDetailView,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeam(idTeam : String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(api.doRequest(TheSportDBApi.getTeam(idTeam?.toInt())).await(), TeamResponse::class.java)
            view.showTeam(data.team)
        }
    }
}