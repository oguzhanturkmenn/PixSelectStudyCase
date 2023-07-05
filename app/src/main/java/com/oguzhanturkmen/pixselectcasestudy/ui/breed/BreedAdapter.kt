package com.oguzhanturkmen.pixselectcasestudy.ui.breed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.pixselectcasestudy.R
import com.oguzhanturkmen.pixselectcasestudy.data.entity.DogData
import com.oguzhanturkmen.pixselectcasestudy.databinding.BreedRowBinding

class BreedAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<BreedAdapter.BreedHolder>() {
    private lateinit var items: ArrayList<DogData>

    fun updateItems(items: ArrayList<DogData>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<BreedRowBinding>(
            inflater, R.layout.breed_row, parent, false
        )
        return BreedHolder(binding)
    }

    // A function used to bind the ViewHolder to a specific position.
    override fun onBindViewHolder(holder: BreedHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class BreedHolder(val binding: BreedRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(p: DogData) {
            binding.executePendingBindings()
            binding.breedmodel = p

            //  OnClickListener that listens for click events and triggers the relevant functions.
            binding.tvBreedName.setOnClickListener {
                if (p.subBreed.isNotEmpty()) {
                    listener.onBreedWithSubKindClick(p.subBreed, p.breed)
                } else {
                    listener.onBreedClick(p.breed)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnItemClickListener {
        fun onBreedWithSubKindClick(subBreed: List<String>, breed: String)
        fun onBreedClick(breed: String)
    }
}