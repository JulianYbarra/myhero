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
            val newList = characterService.getCharacters(PUBLIC_API_KEY, PRIVATE_API_KEY, TS, LIMIT_SIZE, offset).data.results
            characterList = characterList + newList
            emit(characterList)
        }

    }

    companion object {
        private const val LIMIT_SIZE = 15
        private const val PAGE_THRESHOLD = 5
        private const val PUBLIC_API_KEY = "3a783b25c80e1c44875356dd363f272d"
        private const val PRIVATE_API_KEY = "51a3ecf2f92a23817992a2663183325e"
        private const val TS = "1"
    }
}