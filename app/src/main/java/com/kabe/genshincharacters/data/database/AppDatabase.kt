package com.kabe.genshincharacters.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kabe.genshincharacters.data.database.dao.CharactersDao
import com.kabe.genshincharacters.domain.Characters

@Database(
    entities = [Characters::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}