package com.example.todopt2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todopt2.data.Task
import com.example.todopt2.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskDao: TaskDao
): ViewModel() {



    fun insertList(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }


    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.delete(task)
        }
    }



    fun getTaskId (id:Int): LiveData<Task> {
        return taskDao.getId(id).asLiveData()
    }


    val tasks = taskDao.getTask().asLiveData()

    fun insertTaskItem(task: String, complete: Boolean, id: Int) {
        if (task.isEmpty()) {

        }
    }



    // verify if the text in the TextFields are empty or not, toDo is the textfield
    fun isEntryValid(textField: String): Boolean {
        if (textField.isBlank()) {
            return false
        }
        return true
    }

    // assigns whatever string & chechbox state to the Entity
    fun getNewTaskEntry(taskTxt: String, complet: Boolean): Task {
        return Task(
            taskText = taskTxt,
            complete = complet
        )
    }

    // logic to insert data via taskDao
    fun addTask(taskTxt: String, complet: Boolean) {
        val newTask = getNewTaskEntry(taskTxt, complet)
        insertList(newTask)
    }

    // logic to update checkbox in the data live
    fun onTaskChecked(task: Task, isChecked: Boolean) {
        viewModelScope.launch {
        taskDao.update(task.copy(complete = isChecked))
    }
    }
}