package com.junka.myhero.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.junka.myhero.event.repository.EventRepository

class EventViewModel(
    private val eventRepository: EventRepository
) : ViewModel() {

    val eventList = eventRepository.getEvents().asLiveData()

}