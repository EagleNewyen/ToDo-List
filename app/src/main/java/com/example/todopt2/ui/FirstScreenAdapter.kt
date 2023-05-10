package com.example.todopt2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todopt2.data.Task
import com.example.todopt2.databinding.TasksRvBinding

class FirstScreenAdapter
    (private val listener: OnItemClickListener, private val onItemClicked: (Task) -> Unit
): RecyclerView.Adapter<FirstScreenAdapter.TaskViewHolder>() {


    private val diffCallback = object : DiffUtil.ItemCallback<Task>() {

        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.taskText == newItem.taskText
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var task_: List<Task>
        get() = differ.currentList
        set(value) = differ.submitList(value)



    inner class TaskViewHolder(val binding: TasksRvBinding) : RecyclerView.ViewHolder(binding.root) {

       init {
           binding.apply {
               // logic for actual data change when clicking checkbox
               checkBox.setOnClickListener{
                   val position = adapterPosition
                   if(position != RecyclerView.NO_POSITION) {
                       val task = task_[position]
                       listener.onCheckBoxClick(task, checkBox.isChecked)
                   }
               }
                   // Gives the reycyclerview items a clickable action
                       itemView.setOnClickListener {
                           val position = adapterPosition
                           if (position != RecyclerView.NO_POSITION) {
                               val current = task_[position]
                               onItemClicked(current)
                       }
                   }

           }
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TasksRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        // binds item at the correct position
        val task = task_[position]
        holder.binding.apply {
            taskName.text = task.taskText
            taskName.paint.isStrikeThruText = task.complete
            checkBox.isChecked = task.complete
        }

    }



    override fun getItemCount(): Int {
        return task_.size
    }








}