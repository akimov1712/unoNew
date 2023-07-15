package ru.steelwave.unonew.presentaion.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import ru.steelwave.unonew.R
import ru.steelwave.unonew.databinding.FragmentAboutUserBinding
import ru.steelwave.unonew.domain.entity.UserModel
import ru.steelwave.unonew.presentaion.home.HomeViewModelFactory


class AboutUserFragment : Fragment() {

    private var _binding: FragmentAboutUserBinding? = null
    private val binding: FragmentAboutUserBinding
    get() = _binding ?: throw RuntimeException("FragmentAboutUserBinding == null")

    private val args by navArgs<AboutUserFragmentArgs>()

    private val viewModelFactory by lazy {
        AboutUserViewModelFactory(requireActivity().application)
    }
    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[AboutUserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAboutUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser(args.userId)
        observeViewModel()
        setupListenersInView()
    }

    private fun observeViewModel(){
        viewModel.user.observe(viewLifecycleOwner){
            with(binding){
                tvUsername.text = it.name
                tvCountWins.text = it.wins.toString()
                tvRoundWins.text = it.roundsWon.toString()
                tvRecord.text = it.maxPoints.toString()
                tvMinRecord.text = it.minPoints.toString()
                tvTotalPoints.text = it.totalPoints.toString()
            }
        }
    }


    private fun setupListenersInView(){
        binding.btnBack.setOnClickListener{
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}