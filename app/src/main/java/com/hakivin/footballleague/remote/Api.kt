package com.hakivin.footballleague.remote

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class Api {
    fun doRequest(url: String) : Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }
}