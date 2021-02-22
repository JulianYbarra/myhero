package com.junka.myhero.event.repository

import com.junka.myhero.event.model.EventData
import com.junka.myhero.event.service.EventService
import kotlinx.coroutines.flow.flow

class EventRepository(
    private val service: EventService) {

    private var eventList = listOf<EventData>()

    fun checkRequireNewPage(lastVisible: Int) = flow {
        val offset = eventList.size
        if(lastVisible > offset - PAGE_THRESHOLD) {
            val newList = service.getEvents(PUBLIC_API_KEY, PRIVATE_API_KEY, TS, LIMIT_SIZE, offset).data.results
            eventList = eventList + newList
            emit(eventList)
        }

    }

    companion object {
        private const val LIMIT_SIZE = 25
        private const val PAGE_THRESHOLD = 5
        private const val PUBLIC_API_KEY = "3a783b25c80e1c44875356dd363f272d"
        private const val PRIVATE_API_KEY = "51a3ecf2f92a23817992a2663183325e"
        private const val TS = "1"
    }
}