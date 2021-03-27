package ru.kireev.retechlabstesttask.view.adapter.factadapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.kireev.retechlabstesttask.R
import ru.kireev.retechlabstesttask.viewmodel.entity.Fact

class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val factTextView = itemView.findViewById<TextView>(R.id.fact_item_text)
    private val dateTextView = itemView.findViewById<TextView>(R.id.fact_item_date)

    fun onBind(fact: Fact) {
        factTextView.text = fact.text
        dateTextView.text = fact.createdAt
    }
}