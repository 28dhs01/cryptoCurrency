package com.example.crypto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class CryptoListAdapter( private val listener:CryptoItemClicked ): RecyclerView.Adapter<CryptoViewHolder>() {

    private val items: ArrayList<Crypto> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crypto,parent,false)
        val viewHolder = CryptoViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }

        return viewHolder

    }



    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)

    }

    fun updateCrypto (updatedCrypto: ArrayList<Crypto>){
        items.clear()
        items.addAll(updatedCrypto)

        notifyDataSetChanged()
    }
}

class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}

interface CryptoItemClicked{
    fun onItemClicked(item: Crypto)
}