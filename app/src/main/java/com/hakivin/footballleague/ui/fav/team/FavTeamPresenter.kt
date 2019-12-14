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
            }
            view.showFavTeams(fav)
        }
    }
}