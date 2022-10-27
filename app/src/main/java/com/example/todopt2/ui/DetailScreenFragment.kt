package com.example.todopt2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todopt2.data.Task
import com.example.todopt2.databinding.FragmentDetailScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreenFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()
    private val navArg: DetailScreenFragmentArgs by navArgs()
    private var _binding: FragmentDetailScreenBinding? = null
    private val binding get() = _binding!!
    // You will use this property to store information about a single entity
    lateinit var task: Task

    private fun delete() {
        viewModel.deleteTask(task)
        findNavController().navigateUp()
    }

    private fun bind(task: Task) {
        binding.apply {
            textView.text = task.taskText
            deleButton.setOnClickListener { delete() }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navArg.id


        viewModel.getTaskId(id).observe(this.viewLifecycleOwner) { selectedTask ->
            task = selectedTask
            bind(task)
        }

    }

}