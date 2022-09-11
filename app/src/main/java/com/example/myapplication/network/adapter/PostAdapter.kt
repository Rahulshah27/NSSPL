package com.example.myapplication.network.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.presentation.model.Pixas
import com.squareup.picasso.Picasso

class PostAdapter: RecyclerView.Adapter<PostAdapter.VH>() {
    var items:List<Pixas>?=null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class VH(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            with(itemView){
//                breed?.get(adapterPosition)?.let { callable.invoke(it) }


            }
        }

        fun bind(data: Pixas?) {
            val tvTags = itemView.findViewById<TextView>(R.id.tvTags)
            val ivImage = itemView.findViewById<ImageView>(R.id.ivItem)
            with(itemView){
                tvTags.text = data?.tags
                Picasso.get().load(data?.imageUrl).into(ivImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.list_item_pixabay, parent, false))


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items?.get(position))
    }

    override fun getItemCount() = items?.size ?: 0
}