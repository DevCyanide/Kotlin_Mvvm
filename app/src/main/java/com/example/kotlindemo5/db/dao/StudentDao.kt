package com.example.kotlindemo5.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomWarnings
import com.example.kotlindemo5.model.Students

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(students:Students) : Long
    @Query("SELECT * FROM student_table")
    fun getAllStudent(): LiveData<List<Students>>
    @Query("SELECT * FROM student_table WHERE student_number =  :student_numberr AND password = :passwordd")
    suspend fun login(student_numberr:String,passwordd:String) : List<Students>
}