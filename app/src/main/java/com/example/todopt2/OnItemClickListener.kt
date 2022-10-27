package com.example.todopt2.ui

import com.example.todopt2.data.Task

interface OnItemClickListener {
    fun onCheckBoxClick(task: Task, isChecked: Boolean)
}