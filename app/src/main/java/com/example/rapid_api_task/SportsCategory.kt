package com.example.rapid_api_task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sports_categories")
data class SportsCategory(
    val key: String,
    val group: String,
    val title: String,
    val description: String,
    val active: Boolean,
    val has_outrights: Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

