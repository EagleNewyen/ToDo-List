package com.example.todopt2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todopt2.R
import com.example.todopt2.data.Task
import com.example.todopt2.databinding.FragmentFirstScreenTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstScreenTaskFragment : Fragment(R.layout.fragment_first_screen_task), OnItemClickListener {

    private var _binding: FragmentFirstScreenTaskBinding? = null

    // VM charge of holding data bc doesnt get destroyed on orientation change
    private val viewModel: TaskViewModel by viewModels()





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFirstScreenTaskBinding.bind(view)

        val firstScreenAdapter = FirstScreenAdapter(this) {
            val action = FirstScreenTaskFragmentDirections.actionFirstScreenTaskFragmentToDetailScreenFragment(it.id)
            findNavController().navigate(action)
        }

         binding.apply {
             recyclerView.apply {
                 adapter = firstScreenAdapter
                 layoutManager = LinearLayoutManager(this.context)
                 // setHasFixedSize(true)
             }
         }

        viewModel.tasks.observe(viewLifecycleOwner) {
            firstScreenAdapter.task_ = it
        }

        binding.addButton.setOnClickListener {
            val action = FirstScreenTaskFragmentDirections.actionFirstScreenTaskFragmentToAddScreenFragment()
            findNavController().navigate(action)
        }


    }




    // implement fun here but the func is in viewmodel class
    override fun onCheckBoxClick(task: Task, isChecked: Boolean) {
        viewModel.onTaskChecked(task, isChecked)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}