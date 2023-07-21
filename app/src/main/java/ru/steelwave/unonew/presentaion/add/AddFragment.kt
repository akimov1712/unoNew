package ru.steelwave.unonew.presentaion.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import ru.steelwave.unonew.R
import ru.steelwave.unonew.databinding.FragmentAddBinding
import ru.steelwave.unonew.domain.entity.CardModel
import ru.steelwave.unonew.domain.entity.GameModel
import ru.steelwave.unonew.domain.entity.UserModel
import ru.steelwave.unonew.presentaion.add.cardAdapter.CardAdapter
import ru.steelwave.unonew.presentaion.toast.ToastFinishGame


class AddFragment : Fragment() {

    private val args by navArgs<AddFragmentArgs>()
    private val cardList = mutableListOf<CardModel>()

    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding ?: throw RuntimeException("FragmentAddBinding == null")

    private val viewModelFactory by lazy { AddViewModelFactory(requireActivity().application) }
    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[AddViewModel::class.java]
    }
    private var cardAdapter: CardAdapter? = null
    private lateinit var game: GameModel
    private lateinit var user: UserModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupRecyclerView()
        setupListenersInView()
    }

    private fun initViewModel(){
        viewModel.getGame(args.gameId)
        viewModel.getUser(args.userId)
        viewModel.game.observe(viewLifecycleOwner){
            game = it
        }
        viewModel.user.observe(viewLifecycleOwner){
            user = it
            binding.tvUsername.text = it.name
        }
        viewModel.closeFragment.observe(viewLifecycleOwner){
            requireActivity().onBackPressed()
        }
        viewModel.closeFragment.observe(viewLifecycleOwner){
            ToastFinishGame.toastFinishGame(requireContext())
        }
    }

    private fun setupListenersInView(){
        binding.btnClose.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.btnDone.setOnClickListener {
            if (binding.etCountScore.text.isNullOrBlank()){
                val score = binding.tvCountScore.text.toString().toInt()
                if (score <= 0){
                    Toast.makeText(requireContext(), "Ошибка\nКол-во очков должно быть больше 0", Toast.LENGTH_SHORT).show()
                } else{
                    viewModel.updateData(score, args.userId, args.gameId)
                }
            } else {
                val score = binding.etCountScore.text.toString().toInt()
                viewModel.updateData(score, args.userId, args.gameId)
            }
        }
    }

    private fun setupRecyclerView(){
        fillingCardList(cardList)
        with(binding.rvCards){
            setHasFixedSize(true)
            cardAdapter = CardAdapter(cardList)
            adapter = cardAdapter
        }
        setupCardAdapterListeners()
    }

    private fun setupCardAdapterListeners() {
        cardAdapter?.onCardClickListener = { card ->
            card.incrementCountTouch()
            val allPoints = binding.tvCountScore.text.toString().toInt()
            binding.tvCountScore.text = (allPoints + card.valuePoint).toString()
            cardAdapter?.notifyDataSetChanged()
        }

        cardAdapter?.onCardLongClickListener = { card ->
            val allPoints = binding.tvCountScore.text.toString().toInt()
            if (allPoints >= card.valuePoint && card.countTouch > 0) {
                card.decrementCountTouch()
                binding.tvCountScore.text = (allPoints - card.valuePoint).toString()
                cardAdapter?.notifyDataSetChanged()
            }
        }
    }

    private fun fillingCardList(list: MutableList<CardModel>): List<CardModel>{
        list.add(CardModel(1, R.drawable.card_one))
        list.add(CardModel(2, R.drawable.card_two))
        list.add(CardModel(3, R.drawable.card_three))
        list.add(CardModel(4, R.drawable.card_four))
        list.add(CardModel(5, R.drawable.card_five))
        list.add(CardModel(6, R.drawable.card_six))
        list.add(CardModel(7, R.drawable.card_seven))
        list.add(CardModel(8, R.drawable.card_eight))
        list.add(CardModel(9, R.drawable.card_nine))
        list.add(CardModel(20, R.drawable.card_super))
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}