package com.junka.myhero.event.service

import com.junka.myhero.event.model.EventResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface EventService {

    @GET("/v1/public/events")
    suspend fun getEvents(@Query("limit") limit : Int,@Query("offset") offset : Int,@Query("orderBy") orderBy : String = "startDate") : EventResponseData
}