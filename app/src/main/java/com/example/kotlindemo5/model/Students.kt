package com.example.kotlindemo5.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings


@Entity(tableName = "student_table")
data class Students (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name="student_number")
    val student_number : String,
    @ColumnInfo(name="firstname")
    val firstname: String,
    @ColumnInfo(name="lastname")
    val lastname: String,
    @ColumnInfo(name="password")
    val password: String

)