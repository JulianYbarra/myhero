package com.junka.myhero.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.junka.myhero.character.repository.CharacterRepository

class CharacterViewModel(
    private val repository: CharacterRepository
) : ViewModel(){

    val characterList = repository.getCharacter().asLiveData()
}