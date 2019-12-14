package com.hakivin.footballleague.model

import com.google.gson.annotations.SerializedName

data class TableResponse(@SerializedName("table") val standings: List<TableItem>?)
