package com.junka.myhero.character.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThumbnailData(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension : String
) : Parcelable{

    fun getUrl() : String {
        return "$path.$extension"
    }

}