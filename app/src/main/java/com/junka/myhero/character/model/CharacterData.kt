package com.junka.myhero.character.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterData(
    @Json(name = "id") val id : Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description : String,
    @Json(name = "thumbnail") val thumbnail : ThumbnailData,
    @Json(name = "comics") val comics : ComicData
) : Parcelable
