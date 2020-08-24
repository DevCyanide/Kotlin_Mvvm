package com.example.kotlindemo5.db.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlindemo5.db.dao.StudentDao
import com.example.kotlindemo5.model.Students

@Database(entities = [Students::class],version = 1)
abstract class RomDatabase : RoomDatabase() {

    abstract val studentsDao : StudentDao

    companion object{
        @Volatile
        private var INSTANCE : RomDatabase? = null
        fun getInstance(context: Context):RomDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RomDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                }
                return instance
            }
        }

    }
}