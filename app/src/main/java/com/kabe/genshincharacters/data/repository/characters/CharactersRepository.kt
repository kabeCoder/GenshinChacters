package com.kabe.genshincharacters.data.repository.characters

import com.kabe.genshincharacters.data.base.BaseRepository
import com.kabe.genshincharacters.data.base.Resource
import com.kabe.genshincharacters.data.database.AppDatabase
import com.kabe.genshincharacters.domain.Characters
import com.kabe.genshincharacters.data.network.CharactersService
import javax.inject.Inject

class CharactersRepository @Inject constructor (
    private val service: CharactersService,
    private val appDatabase: AppDatabase,
) : BaseRepository() {

    suspend fun getCharacters(page: Int): Resource<List<Characters>> = serviceCall {

        val result = service.getCharacters(page)

        appDatabase.charactersDao().insertCurrentCharacters(result.results)

        result.results

    }

    suspend fun getCachedCharacters(): Resource<List<Characters>> = serviceCall {
        appDatabase.charactersDao().getAllCharacters()
    }

}