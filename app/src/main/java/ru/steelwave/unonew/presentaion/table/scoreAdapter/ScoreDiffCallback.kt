package ru.steelwave.unonew.presentaion.table.scoreAdapter

import androidx.recyclerview.widget.DiffUtil
import ru.steelwave.unonew.domain.entity.ScoreModel

class ScoreDiffCallback: DiffUtil.ItemCallback<ScoreModel>() {

    override fun areItemsTheSame(oldItem: ScoreModel, newItem: ScoreModel): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: ScoreModel, newItem: ScoreModel): Boolean {
        return true
    }
}