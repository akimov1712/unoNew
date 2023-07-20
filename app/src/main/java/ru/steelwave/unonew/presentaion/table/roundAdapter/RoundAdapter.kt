package ru.steelwave.unonew.presentaion.table.roundAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.steelwave.unonew.databinding.ItemRoundBinding
import ru.steelwave.unonew.domain.entity.RoundModel
import ru.steelwave.unonew.domain.entity.ScoreModel
import ru.steelwave.unonew.domain.entity.UserModel
import ru.steelwave.unonew.presentaion.table.scoreAdapter.ScoreAdapter

class RoundAdapter(private val context: Context) :
    ListAdapter<RoundModel, RoundViewHolder>(RoundDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundViewHolder {
        val binding = ItemRoundBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoundViewHolder, position: Int) {
        val roundList = getItem(position)
        with(holder.binding) {
            val adapter = ScoreAdapter(context, roundList.numRoundInGame)
            val newScoreList = mutableListOf(ScoreModel(UserModel(name = ""), 0))
            roundList.scores.forEach {
                newScoreList.add(it)
            }
            adapter.submitList(newScoreList)
            rvPoints.adapter = adapter
        }
    }
}