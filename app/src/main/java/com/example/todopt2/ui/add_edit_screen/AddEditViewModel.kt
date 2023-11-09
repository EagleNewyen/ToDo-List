package com.example.todopt2.ui.add_edit_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todopt2.data.Task
import com.example.todopt2.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val taskDao: TaskDao
): ViewModel() {


    val task = state.get<Task>("task")

    var taskName = state.get<String>("taskName") ?: task?.taskText ?: ""
        set(value) {
            field = value
            state.set("taskName", value)
        }

    var taskInfo = state.get<String>("taskInfo") ?: task?.taskInfo ?: ""
        set(value) {
            field = value
            state.set("taskInfo", value)
        }

    var taskComplete = state.get<String>("taskComplete") ?: task?.complete ?: false
        set(value) {
            field = value
            state.set("taskComplete", value)
        }


    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.delete(task)
        }
    }
    private fun insertList(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }


    fun updateTask() {
        if (task != null) {
            val updatedTask = task.copy(taskText = taskName, taskInfo = taskInfo)
            updatedTask(updatedTask)
        }
    }

    private fun updatedTask(task: Task) = viewModelScope.launch {
        taskDao.update(task)
    }





    // verify if the text in the TextFields are empty or not, toDo is the textfield
    fun isEntryValid(textField: String, textInfoField: String): Boolean {
        if (textField.isBlank() && textInfoField.isBlank()) {
            return false
        }
        return true
    }

    // assigns whatever string & chechbox state to the Entity
    private fun getNewTaskEntry(taskTxt: String, taskInfoTxt: String, complet: Boolean): Task {
        return Task(
            taskText = taskTxt,
            taskInfo = taskInfoTxt,
            complete = complet,

            )
    }


    // logic to INSERT data via taskDao
    fun addTask(taskTxt: String, taskInfoTxt: String, complet: Boolean) {
        val newTask = getNewTaskEntry(taskTxt, taskInfoTxt, complet)
        insertList(newTask)
    }






}