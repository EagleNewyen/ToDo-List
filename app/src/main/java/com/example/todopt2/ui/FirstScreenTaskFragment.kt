package com.example.todopt2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todopt2.R
import com.example.todopt2.data.Task
import com.example.todopt2.databinding.FragmentFirstScreenTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstScreenTaskFragment : Fragment(), OnItemClickListener {

    // VM charge of holding data bc doesnt get destroyed on orientation change
    private val viewModel: TaskViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFirstScreenTaskBinding.bind(view)

        val taskAdapter = FirstScreenAdapter(this) {
            val action = FirstScreenTaskFragmentDirections.actionFirstScreenTaskFragmentToDetailScreenFragment(it.id)
            findNavController().navigate(action)
        }

         binding.apply {
             recyclerView.apply {
                 adapter = taskAdapter
                 layoutManager = LinearLayoutManager(this.context)
                 // setHasFixedSize(true)
             }
         }

        viewModel.tasks.observe(viewLifecycleOwner) {
            taskAdapter.submitList(it)
        }

        binding.addButton.setOnClickListener {
            val action = FirstScreenTaskFragmentDirections.actionFirstScreenTaskFragmentToAddScreenFragment()
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_screen_task, container, false)
    }
    // implement fun here but create func in viewmodel
    override fun onCheckBoxClick(task: Task, isChecked: Boolean) {
        viewModel.onTaskChecked(task, isChecked)
    }

}