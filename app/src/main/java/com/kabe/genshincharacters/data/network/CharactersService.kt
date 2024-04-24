package com.kabe.genshincharacters.data.network

import com.kabe.genshincharacters.data.model.response.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int,
    ) : CharactersResponse
}