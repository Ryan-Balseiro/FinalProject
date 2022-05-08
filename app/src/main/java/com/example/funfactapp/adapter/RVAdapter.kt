package com.example.funfactapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funfactapp.R
import com.example.funfactapp.model.RecyclerList

class RVAdapter: RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    var items = ArrayList<RecyclerList>()

    fun setUpdatedData(items: ArrayList<RecyclerList>){
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvFact = view.findViewById<TextView>(R.id.tv_fact)
        fun bind(data: RecyclerList){
            tvFact.text = data.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_layout,
            parent,
            false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}