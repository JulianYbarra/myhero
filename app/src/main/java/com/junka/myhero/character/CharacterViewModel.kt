package com.junka.myhero.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.junka.myhero.character.repository.CharacterRepository

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel(){

    val characterList = characterRepository.getCharacter().asLiveData()
}