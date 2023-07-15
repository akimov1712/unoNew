package ru.steelwave.unonew.presentaion.add.cardAdapter

import androidx.recyclerview.widget.DiffUtil
import ru.steelwave.unonew.domain.entity.CardModel

class CardDiffCallback: DiffUtil.ItemCallback<CardModel>() {

    override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
        return true
    }
}