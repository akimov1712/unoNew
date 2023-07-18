package ru.steelwave.unonew.presentaion.add.cardAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.steelwave.unonew.R
import ru.steelwave.unonew.databinding.ItemCardUnoBinding
import ru.steelwave.unonew.domain.entity.CardModel

class CardAdapter(private val cardList: List<CardModel>): RecyclerView.Adapter<CardViewHolder>() {

    var onCardClickListener: ((CardModel) -> Unit)? = null
    var onCardLongClickListener: ((CardModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardUnoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        with(holder.binding){
            tvCount.text = card.countTouch.toString()
            btnCard.setImageResource(card.image)
            holder.itemView.setOnClickListener {
                onCardClickListener?.invoke(card)
            }
            holder.itemView.setOnLongClickListener {
                onCardLongClickListener?.invoke(card)
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}