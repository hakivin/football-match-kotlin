package com.hakivin.footballleague.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(@SerializedName("idLeague")
                  val id: Int?,
                  @SerializedName("strLeague")
                  val name: String?,
                  @SerializedName("strLogo")
                  val badge: Int?,
                  @SerializedName("strDescriptionEN")
                  val description: String?): Parcelable