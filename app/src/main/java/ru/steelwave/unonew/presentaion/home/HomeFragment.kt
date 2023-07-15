package ru.steelwave.unonew.presentaion.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.steelwave.unonew.databinding.FragmentHomeBinding
import ru.steelwave.unonew.presentaion.home.gameAdapter.GameAdapter
import ru.steelwave.unonew.presentaion.home.modals.ModalCreateGame
import ru.steelwave.unonew.presentaion.home.modals.ModalCreateUser
import ru.steelwave.unonew.presentaion.home.modals.ModalDeleteUser
import ru.steelwave.unonew.presentaion.home.userAdapter.UserAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding == null")

    private val viewModelFactory by lazy {
        HomeViewModelFactory(requireActivity().application)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }
    private val modalCreateUser by lazy {
        ModalCreateUser(requireContext())
    }
    private val modalCreateGame by lazy {
        ModalCreateGame(requireContext())
    }
    private val modalDeleteUser by lazy {
        ModalDeleteUser(requireContext())
    }
    private val userAdapter by lazy {
        UserAdapter()
    }
    private val gameAdapter by lazy {
        GameAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViews()
        initModals()
        observeViewModel()
    }

    private fun initModals() {
        initModalCreateUser()
        initModalCreateGame()
    }

    private fun initModalCreateGame() {
        binding.btnCreateTable.setOnClickListener {
            modalCreateGame.show()
        }
        modalCreateGame.onCreateGameClickListener = {
            val target = it.text.toString()
            viewModel.addGame(target)
        }
    }

    private fun initModalCreateUser() {
        binding.btnCreateUser.setOnClickListener {
            modalCreateUser.show()
        }
        modalCreateUser.onCreateUserClickListener = {
            val name = it.text.toString()
            viewModel.addUser(name)
        }
    }

    private fun initRecyclerViews() {
        setupUserAdapter()
        setupGameAdapter()
        setupListenerUserAdapter()
        setupListenerGameAdapter()
    }

    private fun setupListenerGameAdapter(){
        gameAdapter.onGameClickListener = {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTableFragment(it.gameId))
        }
    }

    private fun setupListenerUserAdapter() {
        userAdapter.userClickListener = {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAboutUserFragment(it.userId))
        }
        userAdapter.userLongClickListener = { user ->
            modalDeleteUser.show()
            modalDeleteUser.onDeleteUserClickListener = {
                viewModel.deleteUser(user)
                modalDeleteUser.closeModal()
            }
        }
    }

    private fun setupUserAdapter() {
        with(binding.rvUsers) {
            adapter = userAdapter
        }
    }

    private fun setupGameAdapter() {
        with(binding.rvGames) {
            adapter = gameAdapter

            recycledViewPool.setMaxRecycledViews(
                GameAdapter.VIEW_TYPE_ACTIVE,
                GameAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                GameAdapter.VIEW_TYPE_DISACTIVE,
                GameAdapter.MAX_POOL_SIZE
            )
        }
    }

    private fun observeViewModel() {
        viewModel.userList.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
        viewModel.gameList.observe(viewLifecycleOwner) {
            gameAdapter.submitList(it)
            binding.tvCountGames.text = it.size.toString()
            binding.rvGames.post {
                binding.rvGames.smoothScrollToPosition(0)
            }
        }
        viewModel.closeModalGame.observe(viewLifecycleOwner) {
            modalCreateGame.closeModal()
            Toast.makeText(requireContext(), "Создайте хотябы одного участника", Toast.LENGTH_SHORT)
                .show()
        }
        viewModel.closeModalUser.observe(viewLifecycleOwner) {
            modalCreateUser.closeModal()
            Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}