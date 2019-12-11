package com.hakivin.footballleague.remote

object TheSportDBApi {

    fun getLeague(id: Int?) : String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id=$id"
    }

    fun getNextMatch(id: Int?) : String {
        return "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=$id"
    }

    fun getPreviousMatch(id: Int?) : String {
        return "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=$id"
    }

    fun getMatchDetail(idEvent: Int?) : String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=$idEvent"
    }

    fun getMatch(query: String?) : String {
        return "https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=$query"
    }

    fun getTeam(id: Int?) : String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=$id"
    }
}