package com.example.tvapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tvapp.data.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}