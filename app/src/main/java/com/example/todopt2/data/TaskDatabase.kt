package com.example.todopt2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1, exportSchema = false) // set false to not keep schema version history backups

abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    // logic to insert data for preview
    class Callback @Inject constructor(
        // lazy method, will instantiate only when onCreate happens
        private val database: Provider<TaskDatabase>,
        private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        // Will only be created once, when the db is first created
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Eat"))
                dao.insert(Task("Sleep", complete = true))
                dao.insert(Task("Run"))
            }

        }
    }

}