package com.hakivin.footballleague.ui.league

import com.hakivin.footballleague.model.LeagueItem

interface LeagueView {
    fun showLeague(data: List<LeagueItem>?)
}