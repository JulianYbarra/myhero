package com.junka.myhero.character.repository

import com.junka.myhero.character.model.CharacterData
import com.junka.myhero.character.service.CharacterService
import kotlinx.coroutines.flow.flow

class CharacterRepository(
    private val characterService: CharacterService
) {

    private var characterList = listOf<CharacterData>()

    fun checkRequireNewPage(lastVisible: Int) = flow {
        val offset = characterList.size
        if(lastVisible > offset - PAGE_THRESHOLD) {
            val newList = characterService.getCharacters(LIMIT_SIZE, offset).data.results
            characterList = characterList + newList
            emit(characterList)
        }

    }

    companion object {
        private const val LIMIT_SIZE = 15
        private const val PAGE_THRESHOLD = 5
    }
}