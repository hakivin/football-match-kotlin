package com.hakivin.footballleague.ui.league.table

import com.hakivin.footballleague.model.TableItem

interface TableView {
    fun getStandings(standings: List<TableItem>?)
}