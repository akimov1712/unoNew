package ru.steelwave.unonew.presentaion.home.gameAdapter

import androidx.recyclerview.widget.DiffUtil
import ru.steelwave.unonew.domain.entity.GameModel

class GameItemDiffCallback: DiffUtil.ItemCallback<GameModel>() {

    override fun areItemsTheSame(oldItem: GameModel, newItem: GameModel): Boolean {
        return oldItem.gameId == newItem.gameId
    }

    override fun areContentsTheSame(oldItem: GameModel, newItem: GameModel): Boolean {
        return oldItem == newItem
    }
}