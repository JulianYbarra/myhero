package com.junka.myhero.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.junka.myhero.character.model.CharacterData
import com.junka.myhero.event.model.EventData
import com.junka.myhero.event.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventViewModel(
    private val eventRepository: EventRepository
) : ViewModel() {

    val lastVisible = MutableStateFlow(0)
    val eventList = MutableLiveData<List<EventData>>()

    init {
        viewModelScope.launch {
            lastVisible.collect{
                notifyLastVisible(it)
            }
        }
    }

    private suspend fun notifyLastVisible(lastVisible: Int) {
        eventRepository.checkRequireNewPage(lastVisible).collect {
            eventList.value = it
        }
    }
}