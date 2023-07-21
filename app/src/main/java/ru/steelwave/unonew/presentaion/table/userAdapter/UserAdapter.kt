package ru.steelwave.unonew.presentaion.table.userAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import ru.steelwave.unonew.R
import ru.steelwave.unonew.databinding.ItemScoreBinding
import ru.steelwave.unonew.domain.entity.UserModel

class UserAdapter(private val context: Context): ListAdapter<UserModel, UserViewHolder>(UserDiffCallback()) {

    var onUserClickListener: ((UserModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemScoreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userList = getItem(position)
        with(holder.binding){
            if (position == 0){
                tvScore.text = "Игроки"
                val colorStateList = ContextCompat.getColorStateList(context, R.color.white)
                tvScore.backgroundTintList = colorStateList
                tvScore.setTextColor(ContextCompat.getColorStateList(context, R.color.black))
            } else{
                tvScore.text = userList.name
                holder.itemView.setOnClickListener {
                    try {
                        onUserClickListener?.invoke(userList)
                    } catch (e: java.lang.Exception){
                        Toast.makeText(context, "Данного игрока не существует", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}