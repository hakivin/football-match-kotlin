package com.hakivin.footballleague.ui.event

import com.google.gson.Gson
import com.hakivin.footballleague.model.EventItem
import com.hakivin.footballleague.model.EventResponse
import com.hakivin.footballleague.model.TeamItem
import com.hakivin.footballleague.model.TeamResponse
import com.hakivin.footballleague.remote.Api
import com.hakivin.footballleague.util.TestContextProvider
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class EventPresenterTest {
    @Mock
    lateinit var view: EventView

    @Mock
    lateinit var api: Api

    @Mock
    lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    lateinit var presenter: EventPresenter

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view, api, gson, TestContextProvider())
    }

    @Test
    fun getEvent() {
        val data : MutableList<EventItem> = mutableListOf()
        val idEvent = 441613
        val response = EventResponse(data)

        runBlocking {
            Mockito.`when`(api.doRequest(ArgumentMatchers.anyString())).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", EventResponse::class.java)).thenReturn(response)

            presenter.getEvent(idEvent)

            Mockito.verify(view).showEvent(data)
        }
    }

    @Test
    fun getHomeTeam() {
        val data : MutableList<TeamItem> = mutableListOf()
        val idTeam = 441613
        val response = TeamResponse(data)

        runBlocking {
            Mockito.`when`(api.doRequest(ArgumentMatchers.anyString())).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(response)

            presenter.getHomeTeam(idTeam)

            Mockito.verify(view).showHomeTeam(data)
        }
    }

    @Test
    fun getAwayTeam() {
        val data : MutableList<TeamItem> = mutableListOf()
        val idTeam = 441613
        val response = TeamResponse(data)

        runBlocking {
            Mockito.`when`(api.doRequest(ArgumentMatchers.anyString())).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(response)

            presenter.getAwayTeam(idTeam)

            Mockito.verify(view).showAwayTeam(data)
        }
    }
}