package com.example.room.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.User
import com.example.room.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var itemList = mutableListOf<User>()

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(itemList[position]) {
            holder.binding.tvAge.text = age
            holder.binding.tvId.text = id.toString()
            holder.binding.tvName.text = "${firstName.trim()} ${lastName.trim()}"
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateData(itemList: List<User>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }
}