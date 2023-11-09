package com.example.todopt2.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Task (
    @ColumnInfo(name = "List of Task")
    val taskText: String,
    @ColumnInfo(name = " Task Info")
    val taskInfo: String,
    @ColumnInfo(name = "Completed")
    val complete: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
        ) : Parcelable