package com.junka.myhero.event.model

import com.junka.myhero.character.model.ComicData
import com.junka.myhero.character.model.ThumbnailData
import com.squareup.moshi.Json
import java.util.Date

data class EventData(
    @Json(name = "id") val id : Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description : String,
    @Json(name = "start") val start: String,
    @Json(name = "end") val end : String,
    @Json(name = "thumbnail") val thumbnail : ThumbnailData,
    @Json(name = "comics") val comics : ComicData
)