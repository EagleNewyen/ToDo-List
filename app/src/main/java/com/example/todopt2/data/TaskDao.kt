package com.example.todopt2.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun insert(task: Task)

@Update
suspend fun update(task: Task)

@Delete
suspend fun delete(task: Task)

@Query("SELECT * from task")
fun getTask(): Flow<List<Task>>

@Query("SELECT * from task WHERE id = :id")
fun getId(id: Int): Flow<Task>





}