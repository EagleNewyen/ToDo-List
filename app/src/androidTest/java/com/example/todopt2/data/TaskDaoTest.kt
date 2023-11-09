package com.example.todopt2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class TaskDaoTest  {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

     @Inject
     @Named("test_db") // injects db from TestAppModule
     lateinit var database: TaskDatabase
     private lateinit var dao: TaskDao

     @Before
     fun setup() {
         hiltRule.inject() // injects everything
         dao = database.taskDao()

     }

    @After
    fun teardown() {
        database.close()
    }




// if listOf Task is contained in allTask list
    @Test
    fun insertTask() = runTest {
        val taskItem = Task("name", false, id = 1)
        dao.insert(taskItem)


        dao.getTask().test {
            val taskList = awaitItem()
            assertThat(taskList).contains(taskItem)
            cancel()
        }


    }

    @Test
    fun deleteTask() = runTest {
        val taskItem = Task("name", false, id = 1)
        dao.insert(taskItem)
        dao.delete(taskItem)

        dao.getTask().test {
            val taskList = awaitItem()
            assertThat(taskList).doesNotContain(taskItem)
            cancel()
        }
    }

    @Test
    fun updateTask() = runTest {
        val taskItem = Task("name", false, id = 1)
        dao.insert(taskItem)


        val updatedTaskItem = Task("john", false, id = 1)
        dao.update(updatedTaskItem)


        dao.getId(taskItem.id).test{
            val taskList = awaitItem()
            assertThat(updatedTaskItem).isEqualTo(taskList)
            cancel()
        }


    }


}