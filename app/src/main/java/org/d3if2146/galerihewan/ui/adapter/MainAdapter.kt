package org.d3if2146.galerihewan.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView
import org.d3if2146.galerihewan.databinding.ListItemLinearBinding
import org.d3if2146.galerihewan.databinding.ListItemGridBinding
import org.d3if2146.galerihewan.model.Hewan
import java.util.*
import kotlin.collections.ArrayList
@SuppressLint("NotifyDataSetChanged")
class MainAdapter(private val newData: ArrayList<Hewan>,private val isLinearLayoutManager: Boolean): RecyclerView.Adapter<RecyclerView.ViewHolder>(),Filterable {

    private var hewanList = mutableListOf<Hewan>()
    private var hewanFilterList: MutableList<Hewan> = ArrayList()

    init {
        hewanList.clear()
        hewanList.addAll(newData)
        hewanFilterList = hewanList
    }

    lateinit var onItemClick: ((Hewan) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val gridLayoutManager = GridViewHolder.from(parent)
        val linearLayoutManager = LinearViewHolder.from(parent)
        return if(isLinearLayoutManager) linearLayoutManager else gridLayoutManager
    }

    class GridViewHolder private constructor(val binding: ListItemGridBinding): RecyclerView.ViewHolder(binding.root) {
        companion object{
            fun from(parent: ViewGroup): GridViewHolder{
                return GridViewHolder(ListItemGridBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
        fun bind(position: Int,data: ArrayList<Hewan>,onItemClick: ((Hewan) -> Unit)){
            with(binding){
                cardView.setOnClickListener{
                    onItemClick.invoke(data[position])
                }
                nama.text = data[position].nama
                imageView.setImageResource(data[position].imgResId)
            }
        }
    }
    class LinearViewHolder private constructor(val binding: ListItemLinearBinding): RecyclerView.ViewHolder(binding.root) {
        companion object{
            fun from(parent: ViewGroup): LinearViewHolder{
                return LinearViewHolder(ListItemLinearBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
        fun bind(position: Int,data: ArrayList<Hewan>,onItemClick: ((Hewan) -> Unit)){
            val (nama,namaLatin,jenisHewan,imgResId) = data[position]
            with(binding){
                cardView.setOnClickListener{
                    onItemClick.invoke(data[position])
                }
                this.nama.text = nama
                imageView.setImageResource(imgResId)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is LinearViewHolder -> {
                holder.bind(position, hewanFilterList as ArrayList<Hewan>,onItemClick)
            }
            is GridViewHolder -> {
                holder.bind(position, hewanFilterList as ArrayList<Hewan>,onItemClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return hewanFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(char: CharSequence?): FilterResults {
                val charSearch = char.toString()
                if (charSearch.isEmpty()) hewanFilterList = hewanList
                else {
                    val resultList = ArrayList<Hewan>()
                    for(hewan in hewanList){
                        if(hewan.nama.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))
                            || hewan.namaLatin.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))
                            || hewan.jenisHewan.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))){
                            resultList.add(hewan)
                        }
                    }
                    hewanFilterList = resultList
                }
                return FilterResults().apply { values = hewanFilterList }
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                hewanFilterList = if(results?.values == null) {
                    ArrayList()
                } else
                    results.values as ArrayList<Hewan>
                notifyDataSetChanged()
            }
        }
    }
}