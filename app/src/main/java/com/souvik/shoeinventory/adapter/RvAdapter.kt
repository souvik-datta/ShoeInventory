package com.souvik.shoeinventory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.souvik.shoeinventory.R
import com.souvik.shoeinventory.databinding.RowElementProductBinding
import com.souvik.shoeinventory.local.Entity

class RvAdapter(
    val list: ArrayList<Entity>,
    val onItemClick: OnItemClick
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RowElementProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Entity) {
            binding.data = Entity().apply {
                id = data.id
                name = "Product Name: ${data.name}"
                company = "Company Name: ${data.company}"
                description ="Description: ${data.description}"
            }
            binding.shoeSize.text = "Size: ${data.size}"
            binding.executePendingBindings()
            binding.cardView.setOnClickListener {
                onItemClick.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate<RowElementProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_element_product, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClick {
        fun onClick(position: Int)
    }
}