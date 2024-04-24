package com.kabe.genshincharacters.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kabe.genshincharacters.domain.Characters

@Dao
abstract class CharactersDao {

    @Query("SELECT * FROM Characters")
    abstract suspend fun getAllCharacters(): List<Characters>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCurrentCharacters(characters: List<Characters>)
}