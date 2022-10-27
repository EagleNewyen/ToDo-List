package com.example.todopt2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    @ColumnInfo(name = "List of Task")
    val taskText: String,
    @ColumnInfo(name = "Completed")
    val complete: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
        )