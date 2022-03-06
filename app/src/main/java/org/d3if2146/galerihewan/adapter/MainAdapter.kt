package org.d3if2146.galerihewan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.d3if2146.galerihewan.R
import org.d3if2146.galerihewan.databinding.ListItemBinding
import org.d3if2146.galerihewan.model.Hewan
import org.d3if2146.galerihewan.model.getDataHewan

class MainAdapter(): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private val data: List<Hewan> = getDataHewan()

    inner class MainViewHolder(val binding : ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.nama.text = data[position].nama
        holder.binding.namaLatin.text = data[position].namaLatin
        holder.binding.jenisHewan.text = data[position].jenisHewan
        holder.binding.imageView.setImageResource(data[position].imgResId)
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, data[position].nama, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}