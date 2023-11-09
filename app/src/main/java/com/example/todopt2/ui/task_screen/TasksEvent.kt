package com.example.todopt2.ui.task_screen

import com.example.todopt2.data.Task

sealed class TasksEvent {
    data class NavigateToEditScreen(val task: Task): TasksEvent()
}