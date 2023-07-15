package ru.steelwave.unonew.presentaion.home.userAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.steelwave.unonew.databinding.ItemUserBinding
import ru.steelwave.unonew.domain.entity.UserModel

class UserAdapter: ListAdapter<UserModel, UserViewHolder>(UserItemDiffCallback()) {

    var userLongClickListener: ((UserModel) -> Unit)? = null
    var userClickListener: ((UserModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        with(holder.binding){
            tvUserName.text = user.name
            tvUserCountOfWins.text = user.wins.toString()
            tvUserScoreOfRecord.text = user.maxPoints.toString()
        }
        with(holder.itemView){
            setOnClickListener {
                userClickListener?.invoke(user)
            }
            setOnLongClickListener {
                userLongClickListener?.invoke(user)
                true
            }
        }
    }
}