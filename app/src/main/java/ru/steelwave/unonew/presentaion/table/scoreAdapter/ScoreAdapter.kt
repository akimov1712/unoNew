package ru.steelwave.unonew.presentaion.table.scoreAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import ru.steelwave.unonew.R
import ru.steelwave.unonew.databinding.ItemScoreBinding
import ru.steelwave.unonew.domain.entity.ScoreModel

class ScoreAdapter(
    private val context: Context,
    private val numRound: Int
    ): ListAdapter<ScoreModel, ScoreViewHolder>(ScoreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val binding = ItemScoreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val userList = getItem(position)
        with(holder.binding){
            if (position == 0){
                tvScore.text = "Раунд $numRound"
            } else{
                val colorStateList = ContextCompat
                    .getColorStateList(context, R.color.background_item_score_grey)
                tvScore.backgroundTintList = colorStateList
                tvScore.text = userList.countPoints.toString()
            }
        }
    }
}