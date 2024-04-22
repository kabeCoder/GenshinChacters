package com.kabe.genshincharacters.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity("characters")
data class Characters(
    @PrimaryKey
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("rarity") var rarity: String = "",
    @SerializedName("weapon") var weapon: String = "",
    @SerializedName("vision") var vision: String = "",
    @SerializedName("wiki_url") var wikiUrl: String = "",
) : Serializable
