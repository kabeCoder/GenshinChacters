package com.kabe.genshincharacters.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kabe.genshincharacters.databinding.ItemCharactersBinding
import com.kabe.genshincharacters.domain.Characters
import com.kabe.genshincharacters.features.base.BaseRecyclerViewAdapter
import com.kabe.genshincharacters.util.ItemClickListener

class HomeAdapter(
    private val itemClickListener: ItemClickListener<Characters>
): BaseRecyclerViewAdapter<HomeAdapter.HomeViewHolder, Characters>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder = HomeViewHolder(
        ItemCharactersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val characters = mainList[holder.adapterPosition]

        holder.tvName.text = characters.name

        holder.itemView.setOnClickListener { itemClickListener.onItemClick(characters) }
    }

    override fun getItemCount() = mainList.size

    override fun areItemsTheSame(oldItem: Characters, newItem: Characters) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem.name == newItem.name
    }

    inner class HomeViewHolder(itemBinding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val tvName = itemBinding.tvName
    }
}