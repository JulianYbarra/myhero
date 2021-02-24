package com.junka.myhero.character

import androidx.lifecycle.*
import com.junka.myhero.character.model.CharacterData
import com.junka.myhero.character.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel(){

    val characterList = MutableLiveData<List<CharacterData>>()
    val lastVisible = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            lastVisible.collect {
                notifyLastVisible(it)
            }
        }
    }

    private suspend fun notifyLastVisible(lastVisible: Int) {
        characterRepository.checkRequireNewPage(lastVisible).collect {
            characterList.value = it
        }
    }
}