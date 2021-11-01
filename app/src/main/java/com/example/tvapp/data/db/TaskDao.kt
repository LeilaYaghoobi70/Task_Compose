package com.example.tvapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.tvapp.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskEntity")
    fun getTask(): Flow<List<Task>>

    @Insert(onConflict = REPLACE)
    suspend fun insertTask(task: Task)
}