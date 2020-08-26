package com.example.kotlindemo5.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings
import com.google.gson.annotations.SerializedName


@Entity(tableName = "student_table")
data class Students (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @SerializedName("student_num")
    val student_number : String,
    val firstname: String,
    val lastname: String,
    val password: String

)