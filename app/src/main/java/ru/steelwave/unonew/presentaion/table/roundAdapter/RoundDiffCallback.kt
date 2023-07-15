package ru.steelwave.unonew.presentaion.table.roundAdapter

import androidx.recyclerview.widget.DiffUtil
import ru.steelwave.unonew.domain.entity.RoundModel
import ru.steelwave.unonew.domain.entity.UserModel

class RoundDiffCallback: DiffUtil.ItemCallback<RoundModel>() {

    override fun areItemsTheSame(oldItem: RoundModel, newItem: RoundModel): Boolean {
        return oldItem.roundId == newItem.roundId
    }

    override fun areContentsTheSame(oldItem: RoundModel, newItem: RoundModel): Boolean {
        return oldItem == newItem
    }
}