package com.hakivin.footballleague.ui.league.match.next

import com.google.gson.Gson
import com.hakivin.footballleague.model.EventItem
import com.hakivin.footballleague.model.EventResponse
import com.hakivin.footballleague.model.MatchResponse
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

class NextMatchPresenterTest {
    @Mock
    lateinit var view: NextMatchView

    @Mock
    lateinit var api: Api

    @Mock
    lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    lateinit var presenter: NextMatchPresenter

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, api, gson, TestContextProvider())
    }

    @Test
    fun getNextMatches(){
        val data : MutableList<EventItem> = mutableListOf()
        val idLeague = 4328
        val response = EventResponse(data)

        runBlocking {
            Mockito.`when`(api.doRequest(ArgumentMatchers.anyString())).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", EventResponse::class.java)).thenReturn(response)

            presenter.getNextMatches(idLeague)

            Mockito.verify(view).showEvents(data)
        }
    }

    @Test
    fun searchMatches(){
        val data : MutableList<EventItem> = mutableListOf()
        val query = "City"
        val response = MatchResponse(data)

        runBlocking {
            Mockito.`when`(api.doRequest(ArgumentMatchers.anyString())).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", MatchResponse::class.java)).thenReturn(response)

            presenter.searchMatches(query)

            Mockito.verify(view).showEvents(data)
        }
    }

}