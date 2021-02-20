package com.junka.myhero.character.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicData(
    @Json(name = "available") val available : Int,
    @Json(name = "returned") val returned : Int,
    @Json(name = "collectionURI") val collectionURI : String,
    @Json(name = "items") val items : List<ComicSummaryData>
) : Parcelable