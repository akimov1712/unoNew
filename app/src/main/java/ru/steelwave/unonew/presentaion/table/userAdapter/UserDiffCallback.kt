package ru.steelwave.unonew.presentaion.table.userAdapter

import androidx.recyclerview.widget.DiffUtil
import ru.steelwave.unonew.domain.entity.UserModel

class UserDiffCallback: DiffUtil.ItemCallback<UserModel>() {

    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }
}