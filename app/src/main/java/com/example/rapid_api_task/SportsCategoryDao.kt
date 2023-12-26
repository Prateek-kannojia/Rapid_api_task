package com.example.rapid_api_task

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SportsCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<SportsCategory>)

    @Query("SELECT * FROM sports_categories")
    suspend fun getAllCategories(): List<SportsCategory>
}

