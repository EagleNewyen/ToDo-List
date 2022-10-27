package com.example.todopt2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todopt2.R
import com.example.todopt2.databinding.FragmentAddScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddScreenFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

    private var  _binding: FragmentAddScreenBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun textFieldEmpty(): Boolean {
        return viewModel.isEntryValid(
            binding.textInputLayout.toString()
        )
    }

    private fun saveButtonLog() {
        if(textFieldEmpty()) {
            viewModel.addTask(
                binding.textInput.text.toString(),
                binding.checkBoxAdd.isChecked
            )
            val action = AddScreenFragmentDirections.actionAddScreenFragmentToFirstScreenTaskFragment()
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            saveButton.setOnClickListener { saveButtonLog() }
        }
    }

}