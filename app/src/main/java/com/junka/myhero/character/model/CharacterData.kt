package com.junka.myhero.character.model

import com.squareup.moshi.Json

data class CharacterData(
    @Json(name = "id") val id : Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description : String,
    @Json(name = "thumbnail") val thumbnail : ThumbnailData
)
