package ru.kireev.retechlabstesttask.view.adapter.factadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.kireev.retechlabstesttask.R
import ru.kireev.retechlabstesttask.viewmodel.entity.Fact

class FactListAdapter(
    diffUtilCallback: FactDiffUtilCallback = FactDiffUtilCallback()
) : ListAdapter<Fact, FactViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fact_item, parent, false)
        return FactViewHolder(view)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}