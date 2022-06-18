package com.example.simpleapplication.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simpleapplication.databinding.FragmentStartBinding
import com.example.simpleapplication.ui.start.viewmodel.StartViewModel
import com.example.simpleapplication.ui.start.viewmodel.UIState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type

@AndroidEntryPoint
class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    private val viewModel: StartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Saved -> {
                    binding.textView.text = it.value
                    Snackbar.make(requireContext(), binding.root, "Saved!!!", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.btn.setOnClickListener {
            viewModel.save()
        }


        binding.editText.addTextChangedListener {
            viewModel.onEvent(StartEvent.TypedEvent(it.toString()))
        }
        return binding.root
    }
}