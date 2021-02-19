package com.junka.myhero.character.model

import com.squareup.moshi.Json

data class CharactersResponseData(
    @Json(name ="code") val code : Int,
    @Json(name ="status") val status : String,
    @Json(name ="copyright") val copyright : String,
    @Json(name ="attributionText") val attributionText : String,
    @Json(name ="attributionHTML") val attributionHTML : String,
    @Json(name ="etag") val etag : String,
    @Json(name ="data") val data : DataResponseData
)
