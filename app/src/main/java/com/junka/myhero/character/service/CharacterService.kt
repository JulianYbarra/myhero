package com.junka.myhero.character.service

import com.junka.myhero.character.model.CharactersResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("apikey") apikey: String,@Query("hash") hash: String, @Query("ts") ts: String,@Query("limit") limit : Int,@Query("offset") offset : Int) : CharactersResponseData

}