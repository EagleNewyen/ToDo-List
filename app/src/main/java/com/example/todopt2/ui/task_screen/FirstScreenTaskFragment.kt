package com.example.todopt2.ui.task_screen

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todopt2.R
import com.example.todopt2.data.Task
import com.example.todopt2.databinding.FragmentFirstScreenTaskBinding
import com.example.todopt2.ui.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FirstScreenTaskFragment : Fragment(R.layout.fragment_first_screen_task), OnItemClickListener,PopupMenu.OnMenuItemClickListener {

    private var _binding: FragmentFirstScreenTaskBinding? = null

    // VM charge of holding data bc doesnt get destroyed on orientation change
    private val viewModel: TaskViewModel by viewModels()

    private lateinit var selectedTask: Task






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFirstScreenTaskBinding.bind(view)

        val firstScreenAdapter = FirstScreenAdapter(this)

         binding.apply {
             recyclerView.apply {
                 adapter = firstScreenAdapter
                 layoutManager = GridLayoutManager(this.context, 2)
                 // setHasFixedSize(true)
             }
         }

        viewModel.tasks.observe(viewLifecycleOwner) {
            firstScreenAdapter.task_ = it
        }

        // Add Button FLoating action
        binding.addButton.setOnClickListener {
            val action =
                FirstScreenTaskFragmentDirections.actionFirstScreenTaskFragmentToAddScreenFragment()
            findNavController().navigate(action)
        }
        // Task onClick
        lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tasksEvent.collect { event ->
                    when(event) {
                        is TasksEvent.NavigateToEditScreen -> {
                            val action =
                                FirstScreenTaskFragmentDirections.actionFirstScreenTaskFragmentToDetailScreenFragment(event.task)
                            findNavController().navigate(action)
                        }
                    }

                }

            }
        }

    }




    // implement fun here but the func is in viewmodel class
    override fun onCheckBoxClick(task: Task, isChecked: Boolean) {
        viewModel.onTaskChecked(task, isChecked)
    }

    override fun longPressDelete(task: Task, cardView: CardView) {
        popUpDisplay(cardView)
        selectedTask = task
    }

    override fun onItemClick(task: Task) {
        viewModel.onTaskSelected(task)
    }


    private fun popUpDisplay(cardView: CardView) {
        val popup = PopupMenu(context,cardView)
        popup.setOnMenuItemClickListener(this@FirstScreenTaskFragment)
        popup.inflate(R.menu.pop_up_menu)
        popup.show()
    }
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.delete_popUp) {
            viewModel.deleteTask(selectedTask)
            return true
}
        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
