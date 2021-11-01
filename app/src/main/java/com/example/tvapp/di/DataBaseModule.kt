package com.example.tvapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tvapp.data.db.TaskDao
import com.example.tvapp.data.model.Task
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

const val DB_NAME = "TASK"
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Database(entities = [Task::class], version = 1)
    abstract class TaskDataBase : RoomDatabase() {
        abstract fun taskDao(): TaskDao
    }

    @Provides
    fun taskDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        TaskDataBase::class.java,
        DB_NAME
    ).build().taskDao()
}