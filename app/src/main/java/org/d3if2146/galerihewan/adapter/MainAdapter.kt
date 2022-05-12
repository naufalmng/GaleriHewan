package org.d3if2146.galerihewan.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import org.d3if2146.galerihewan.databinding.ListItemBinding
import org.d3if2146.galerihewan.model.Hewan
import java.util.*
import kotlin.collections.ArrayList
@SuppressLint("NotifyDataSetChanged")
class MainAdapter(): RecyclerView.Adapter<MainAdapter.MainViewHolder>(),Filterable {

    private var hewanList = mutableListOf<Hewan>()
    private var hewanFilterList: MutableList<Hewan> = ArrayList()

    init {
        hewanFilterList = hewanList
    }


    fun updateData(newData: ArrayList<Hewan>){
        hewanList.clear()
        hewanList.addAll(newData)
        notifyDataSetChanged()
    }

    var onItemClick: ((Hewan) -> Unit)? = null

    inner class MainViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.nama.text = hewanFilterList[position].nama
        holder.binding.namaLatin.text = hewanFilterList[position].namaLatin
        holder.binding.jenisHewan.text = hewanFilterList[position].jenisHewan
        holder.binding.imageView.setImageResource(hewanFilterList[position].imgResId)
//        holder.itemView.setOnClickListener {
//            onItemClick!!.invoke(hewanFilterList[position])
//        }
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