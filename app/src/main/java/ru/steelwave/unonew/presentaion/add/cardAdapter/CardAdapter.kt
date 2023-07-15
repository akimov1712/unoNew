package ru.steelwave.unonew.presentaion.add.cardAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.steelwave.unonew.R
import ru.steelwave.unonew.databinding.ItemCardUnoBinding
import ru.steelwave.unonew.domain.entity.CardModel

class CardAdapter: ListAdapter<CardModel, CardViewHolder>(CardDiffCallback()) {

    var onCardClickListener: ((CardModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardUnoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = getItem(position)
        with(holder.binding){
            tvCount.text = card.valuePoint.toString()
            btnCard.setImageResource(R.drawable.card_super)
            holder.itemView.setOnClickListener {
                onCardClickListener?.invoke(card)
            }
        }
    }
}