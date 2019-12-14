package com.hakivin.footballleague.ui.league.table

import com.google.gson.Gson
import com.hakivin.footballleague.model.SeasonResponse
import com.hakivin.footballleague.model.TableResponse
import com.hakivin.footballleague.remote.Api
import com.hakivin.footballleague.remote.TheSportDBApi
import com.hakivin.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TablePresenter(private val view: TableView,
                     private val api: Api,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getStandings(idLeague: Int?){
        GlobalScope.launch(context.main) {
            val seasons = gson.fromJson(api.doRequest(TheSportDBApi.getSeason(idLeague)).await(), SeasonResponse::class.java)
            val data = gson.fromJson(api.doRequest(TheSportDBApi.getStandings(idLeague, seasons.seasons?.last()?.season)).await(), TableResponse::class.java)
            view.getStandings(data.standings)
        }
    }
}