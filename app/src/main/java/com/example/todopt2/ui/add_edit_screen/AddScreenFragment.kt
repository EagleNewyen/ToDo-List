package com.example.todopt2.ui.add_edit_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todopt2.Constants.ERROR_MESSAGE
import com.example.todopt2.databinding.FragmentAddScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddScreenFragment : Fragment() {

    private val viewModel: AddEditViewModel by viewModels()

    private var  _binding: FragmentAddScreenBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun textFieldEmpty(): Boolean {
        return viewModel.isEntryValid(
            binding.textInputLayout.toString(),
            binding.textInputInfoLayout.toString()
        )
    }

      private fun saveButtonLog() {
        if(textFieldEmpty()) {
            viewModel.addTask(
                binding.textInput.text.toString(),
                binding.textInfoInput.text.toString(),
                binding.checkBoxAdd.isChecked
            )
            val action =
                AddScreenFragmentDirections.actionAddScreenFragmentToFirstScreenTaskFragment()
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            saveButton.setOnClickListener {
                val taskTxt = binding.textInput.text.toString()
                if(taskTxt.isEmpty()){
                    binding.textInput.error = ERROR_MESSAGE
                    return@setOnClickListener
                }
                saveButtonLog()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}