package com.hakivin.footballleague.util

import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { Main }
}

class TestContextProvider : CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}