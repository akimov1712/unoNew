package ru.steelwave.unonew.presentaion.home.gameAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.steelwave.unonew.R
import ru.steelwave.unonew.databinding.ItemGameActiveBinding
import ru.steelwave.unonew.databinding.ItemGameDisactiveBinding
import ru.steelwave.unonew.domain.entity.GameModel
import ru.steelwave.unonew.utils.convertLongToDate
import ru.steelwave.unonew.utils.convertLongToTime
import java.text.SimpleDateFormat
import java.util.*

class GameAdapter():ListAdapter<GameModel, GameViewHolder>(GameItemDiffCallback()) {

    var onGameClickListener:((GameModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_ACTIVE, VIEW_TYPE_FINISH ->{
                R.layout.item_game_active
            }
            VIEW_TYPE_DISACTIVE ->{
                R.layout.item_game_disactive
            } else -> {
                throw RuntimeException("View type is not undefined")
            }
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val gameList = getItem(position)
        with(holder){
            val leaderName = gameList.leadingUser.user.name
            val leaderScore = gameList.leadingUser.countPoints
            if (gameList.isFinished){
                tvNameWinner.text = "Победитель $leaderName"
            } else {
                tvNameWinner.text = "$leaderName - $leaderScore"
            }
            tvNumberOfGame.text = gameList.gameId.toString()
            tvDate.text = convertLongToDate(gameList.creationDate)
            tvTime.text = convertLongToTime(gameList.creationDate)
        }
        holder.itemView.setOnClickListener {
            onGameClickListener?.invoke(gameList)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val gameList = getItem(position)
        return if(gameList.isFinished){
            VIEW_TYPE_FINISH
        } else if (position == 0){
            VIEW_TYPE_ACTIVE
        } else{
            VIEW_TYPE_DISACTIVE
        }
    }

    companion object{
        const val VIEW_TYPE_ACTIVE = 1
        const val VIEW_TYPE_FINISH = 0
        const val VIEW_TYPE_DISACTIVE = -1
        const val MAX_POOL_SIZE = 0
    }

}