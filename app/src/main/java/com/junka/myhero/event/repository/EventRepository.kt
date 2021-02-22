package com.junka.myhero.event.repository

import com.junka.myhero.event.model.EventData
import com.junka.myhero.event.service.EventService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventRepository(
    private val service: EventService) {

    fun getEvents() : Flow<List<EventData>> = flow {
        emit(service.getEvents(PUBLIC_API_KEY, PRIVATE_API_KEY,TS).data.results)
    }

    companion object{
        private const val PAGING_SIZE = 15
        private const val PUBLIC_API_KEY = "3a783b25c80e1c44875356dd363f272d"
        private const val PRIVATE_API_KEY = "51a3ecf2f92a23817992a2663183325e"
        private const val TS = "1"
    }
}