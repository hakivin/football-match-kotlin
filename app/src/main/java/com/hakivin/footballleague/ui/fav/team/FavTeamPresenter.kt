package com.hakivin.footballleague.ui.fav.team

import com.hakivin.footballleague.db.DatabaseHelper
import com.hakivin.footballleague.model.TeamItem
import com.hakivin.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavTeamPresenter (private val view: FavTeamView,
                        private val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getFavTeams(db : DatabaseHelper){
        GlobalScope.launch(context.main){
            var fav : List<TeamItem>? = null
            db.use {
                val result = select(TeamItem.TABLE_TEAM)
                fav = result.parseList(classParser())
                println("debug1 = ${fav!![0].id}")
                println("debug2 = ${fav!![0].name}")
                println("debug3 = ${fav!![0].alternate}")
                println("debug4 = ${fav!![0].banner}")
                println("debug5 = ${fav!![0].desc}")
                println("debug6 = ${fav!![0].formedYear}")
                println("debug7 = ${fav!![0].logo}")
                println("debug8 = ${fav!![0].location}")
                println("debug9 = ${fav!![0].stadium}")
                println("debug10 = ${fav!![0].manager}")
                println("debug11 = ${fav!![0].type}")
            }
            view.showFavTeams(fav)
        }
    }
}