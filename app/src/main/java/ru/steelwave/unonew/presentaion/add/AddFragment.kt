package ru.steelwave.unonew.presentaion.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import ru.steelwave.unonew.R
import ru.steelwave.unonew.databinding.FragmentAddBinding
import ru.steelwave.unonew.domain.entity.CardModel
import ru.steelwave.unonew.presentaion.add.cardAdapter.CardAdapter


class AddFragment : Fragment() {

    private val args by navArgs<AddFragmentArgs>()
    private val cardList = mutableListOf<CardModel>()

    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding ?: throw RuntimeException("FragmentAddBinding == null")

    private val cardAdapter by lazy {
        CardAdapter()
    }

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
        setupViews()
        setupRecyclerView()
    }

    private fun initViewModel(){

    }
    private fun setupViews(){
        binding.tvUsername.text
    }

    private fun setupRecyclerView(){
        fillingCardList()
        with(binding.rvCards){
            setHasFixedSize(true)
            cardAdapter.submitList(cardList)
            adapter = cardAdapter
        }
    }
    private fun fillingCardList(){
        cardList.add(CardModel(1, R.drawable.card_one))
        cardList.add(CardModel(2, R.drawable.card_two))
        cardList.add(CardModel(3, R.drawable.card_three))
        cardList.add(CardModel(4, R.drawable.card_four))
        cardList.add(CardModel(5, R.drawable.card_five))
        cardList.add(CardModel(6, R.drawable.card_six))
        cardList.add(CardModel(7, R.drawable.card_seven))
        cardList.add(CardModel(8, R.drawable.card_eight))
        cardList.add(CardModel(9, R.drawable.card_nine))
        cardList.add(CardModel(20, R.drawable.card_super))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}