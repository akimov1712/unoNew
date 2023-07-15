package ru.steelwave.unonew.presentaion.table

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.steelwave.unonew.R
import ru.steelwave.unonew.databinding.FragmentHomeBinding
import ru.steelwave.unonew.databinding.FragmentTableBinding


class TableFragment : Fragment() {

    private var _binding: FragmentTableBinding? = null
    private val binding: FragmentTableBinding
        get() = _binding ?: throw RuntimeException("FragmentTableBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}