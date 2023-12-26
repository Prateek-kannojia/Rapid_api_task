package com.example.rapid_api_task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SportsCategoryAdapter(var sportsCategories: List<SportsCategory>) :
    RecyclerView.Adapter<SportsCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sports_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sportsCategory = sportsCategories[position]

        holder.titleTextView.text = sportsCategory.title
        holder.groupTextView.text = sportsCategory.group
        holder.descriptionTextView.text = sportsCategory.description
        holder.activeTextView.text = "Active: ${sportsCategory.active}"
        holder.outrightsTextView.text = "Has Outrights: ${sportsCategory.has_outrights}"
    }

    override fun getItemCount(): Int {
        return sportsCategories.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val groupTextView: TextView = itemView.findViewById(R.id.groupTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val activeTextView: TextView = itemView.findViewById(R.id.activeTextView)
        val outrightsTextView: TextView = itemView.findViewById(R.id.outrightsTextView)
    }
}
