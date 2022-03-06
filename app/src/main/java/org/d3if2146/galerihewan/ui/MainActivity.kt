package org.d3if2146.galerihewan.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.d3if2146.galerihewan.adapter.MainAdapter
import org.d3if2146.galerihewan.databinding.ActivityMainBinding

@SuppressLint("NotifyDataSetChanged")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mainAdapter = MainAdapter()
        prepareRecyclerView()
      }


    private fun prepareRecyclerView() {
        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context,RecyclerView.VERTICAL))
        }
        mainAdapter.notifyDataSetChanged()
    }
}