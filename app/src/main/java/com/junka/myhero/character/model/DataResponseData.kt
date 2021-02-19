package com.junka.myhero.character.model

import com.squareup.moshi.Json

data class DataResponseData(
    @Json(name ="offset") val offset : Int,
    @Json(name ="limit") val limit : Int,
    @Json(name ="total") val total : Int,
    @Json(name ="count") val count : Int,
    @Json(name ="results") val results : List<CharacterData>
)