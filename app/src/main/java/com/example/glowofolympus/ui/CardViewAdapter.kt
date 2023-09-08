package com.example.glowofolympus.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.glowofolympus.databinding.ItemCardBinding
import com.example.glowofolympus.domain.CardModel

class CardViewAdapter(data: List<CardModel>, private val onClick:(Int) -> Unit) :
    RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {
    private var list: List<CardModel> = data
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class CardViewHolder(
        val binding: ItemCardBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)

        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = list[position]

        holder.binding.ivCard.setImageResource(item.image)
        holder.binding.btnHeart.setImageResource(item.heart)
        holder.binding.tvTitleCard.setText(item.title)
        holder.binding.tvDescriptionCard.setText(item.description)

        holder.binding.ivCard.setOnClickListener {
            onClick(position)
        }
    }
}

