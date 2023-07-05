package com.oguzhanturkmen.pixselectcasestudy.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.pixselectcasestudy.R
import com.oguzhanturkmen.pixselectcasestudy.databinding.DetailsRowBinding

class DetailsAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<DetailsAdapter.DetailHolder>() {

    var images: ArrayList<String>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<DetailsRowBinding>(
            inflater, R.layout.details_row, parent, false
        )
        return DetailHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsAdapter.DetailHolder, position: Int) {
        val item = images?.get(position)
        holder.bind(item)
    }

    inner class DetailHolder(val binding: DetailsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(img: String?) {
            binding.executePendingBindings()
            binding.imageData = img

            binding.imgCircleDog.setOnClickListener {
                listener.imgCircleClicked(binding.imageData!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return images?.size ?: 0
    }

    interface OnItemClickListener {
        fun imgCircleClicked(img: String)
    }
}