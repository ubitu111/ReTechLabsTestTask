package ru.kireev.retechlabstesttask.view.adapter.factadapter

import androidx.recyclerview.widget.DiffUtil
import ru.kireev.retechlabstesttask.viewmodel.entity.Fact

class FactDiffUtilCallback : DiffUtil.ItemCallback<Fact>() {
    override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean {
        return oldItem.text == newItem.text && oldItem.createdAt == newItem.createdAt
    }
}