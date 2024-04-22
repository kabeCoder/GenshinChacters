package com.kabe.genshincharacters.data.model.response

import com.google.gson.annotations.SerializedName
import com.kabe.genshincharacters.domain.Characters

data class CharactersResponse(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<Characters>,
    @SerializedName("total_results") var totalResults: Int,
    @SerializedName("total_pages") var totalPages: Int,

)
