package com.hakivin.footballleague.ui.league

import com.google.gson.Gson
import com.hakivin.footballleague.model.LeagueItem
import com.hakivin.footballleague.model.LeagueResponse
import com.hakivin.footballleague.remote.Api
import com.hakivin.footballleague.remote.TheSportDBApi
import com.hakivin.footballleague.util.TestContextProvider
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeaguePresenterTest {

    @Mock
    lateinit var view: LeagueView

    @Mock
    lateinit var api: Api

    @Mock
    lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    lateinit var presenter: LeaguePresenter

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        presenter = LeaguePresenter(view, api, gson, TestContextProvider())
    }

    @Test
    fun getLeague() {
        val data : MutableList<LeagueItem> = mutableListOf()
        val idLeague = 4328
        val response = LeagueResponse(data)

        runBlocking {
            Mockito.`when`(api.doRequest(ArgumentMatchers.anyString())).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", LeagueResponse::class.java)).thenReturn(response)

            presenter.getLeague(idLeague)

            Mockito.verify(view).showLeague(data)
        }
    }
}