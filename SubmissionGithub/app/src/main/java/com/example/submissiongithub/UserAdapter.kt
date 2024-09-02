package com.example.submissiongithub

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.submissiongithub.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    private val list = ArrayList<User>()

    private var onItemClickBack : OnItemClickBack? = null

    fun setOnItemClickCallBack(onItemClickBack: OnItemClickBack){
        this.onItemClickBack = onItemClickBack
    }

    fun setlist(users: ArrayList<User>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()

    }


    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user:User){

            binding.root.setOnClickListener {
                onItemClickBack?.onItemClicked(user)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivUser)
                tvUsername.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder((view))
    }


    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickBack{
        fun onItemClicked(data:User)
    }
}