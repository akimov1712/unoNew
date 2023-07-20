package ru.steelwave.unonew.presentaion.table

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.steelwave.unonew.R
import ru.steelwave.unonew.databinding.FragmentHomeBinding
import ru.steelwave.unonew.databinding.FragmentTableBinding
import ru.steelwave.unonew.domain.entity.GameModel
import ru.steelwave.unonew.domain.entity.RoundModel
import ru.steelwave.unonew.domain.entity.ScoreModel
import ru.steelwave.unonew.domain.entity.UserModel
import ru.steelwave.unonew.presentaion.table.roundAdapter.RoundAdapter
import ru.steelwave.unonew.presentaion.table.userAdapter.UserAdapter


class TableFragment : Fragment() {

    private val args by navArgs<TableFragmentArgs>()

    private var _binding: FragmentTableBinding? = null
    private val binding: FragmentTableBinding
        get() = _binding ?: throw RuntimeException("FragmentTableBinding == null")

    private val viewModelFactory by lazy {
        TableViewModelFactory(requireActivity().application)
    }
    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[TableViewModel::class.java]
    }

    private val userAdapter by lazy {
        UserAdapter(requireContext())
    }
    private val roundAdapter by lazy {
        RoundAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListenerInViews()
        initViewModel()
    }

    private fun initViewModel(){
        observeViewModel()
        viewModel.getGame(args.gameId)
    }

    private fun observeViewModel() {
        viewModel.game.observe(viewLifecycleOwner) {
            binding.tvTarget.text = it.targetPoints.toString()
            binding.tvNumberOfGame.text = "№ " + it.gameId.toString()
            setupRecyclerView(it)
        }
        viewModel.roundList.observe(viewLifecycleOwner){
            setupRoundAdapter(it)
        }
    }

    private fun setupRecyclerView(game: GameModel){
        setupUserAdapter(game)
        setupUserAdapterListeners(game)
    }

    private fun setupUserAdapterListeners(game: GameModel){
        userAdapter.onUserClickListener = { user ->
            findNavController().navigate(
                TableFragmentDirections.actionTableFragmentToAddFragment(user.userId, game.gameId)
            )
        }
    }

    private fun setupUserAdapter(game: GameModel){
        val userList = mutableListOf(UserModel(name = ""))
        game.participants.forEach{
            userList.add(it)
        }
        userAdapter.submitList(userList)
        binding.rvUsers.adapter = userAdapter
    }

    private fun setupRoundAdapter(roundList: List<RoundModel>){
        roundAdapter.submitList(roundList)
        binding.rvRounds.adapter = roundAdapter
    }

    private fun setupListenerInViews(){
        binding.btnClose.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}