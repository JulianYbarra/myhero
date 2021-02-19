package com.junka.myhero.character.repository

import com.junka.myhero.character.model.CharacterData
import com.junka.myhero.character.service.CharacterService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepository(
    private val characterService: CharacterService) {

    fun getCharacter() : Flow<List<CharacterData>> = flow {
        emit(characterService.getCharacters(PUBLIC_API_KEY, PRIVATE_API_KEY,TS).data.results)
    }

    companion object{
        private const val PAGING_SIZE = 15
        private const val PUBLIC_API_KEY = "3a783b25c80e1c44875356dd363f272d"
        private const val PRIVATE_API_KEY = "51a3ecf2f92a23817992a2663183325e"
        private const val TS = "1"
    }
}