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
            val newList = service.getEvents(LIMIT_SIZE, offset).data.results
            eventList = eventList + newList
            emit(eventList)
        }

    }

    companion object {
        private const val LIMIT_SIZE = 25
        private const val PAGE_THRESHOLD = 5
    }
}