package com.kabe.genshincharacters.repository.characters

import com.kabe.genshincharacters.data.base.BaseRepository
import com.kabe.genshincharacters.data.base.Resource
import com.kabe.genshincharacters.domain.Characters
import com.kabe.genshincharacters.network.CharactersService
import javax.inject.Inject

class CharactersRepository @Inject constructor (
    private val service: CharactersService,
) : BaseRepository() {

    suspend fun getCharacters(page: Int): Resource<List<Characters>> = serviceCall {

        val result = service.getCharacters(page)

        result.results

    }

}