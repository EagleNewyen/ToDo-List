package com.example.todopt2.ui

import androidx.cardview.widget.CardView
import com.example.todopt2.data.Task

interface OnItemClickListener {
    fun onCheckBoxClick(task: Task, isChecked: Boolean)
    fun longPressDelete(task: Task, cardView: CardView)
    fun onItemClick(task: Task)

}