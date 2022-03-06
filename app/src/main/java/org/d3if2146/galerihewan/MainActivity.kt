package org.d3if2146.galerihewan

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import org.d3if2146.galerihewan.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var index = 0
    private val hewan = listOf("Kambing","Ayam","Bebek","Domba","Sapi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupListeners()
      }

    private fun showBtnNext(){
        index = if(index == hewan.size-1) 0 else index+1
        imgTextSlider()
    }

    private fun showBtnPrev(){
        index = if(index == hewan.size-1) 3 else index-1
        binding.tvHewan.text = hewan[index]
        imgTextSlider()
    }

    private fun imgTextSlider(){
        val resourceId = when(index){
            0 -> R.drawable.kambing
            1 -> R.drawable.ayam
            2 -> R.drawable.bebek
            3 -> R.drawable.domba
            4 -> R.drawable.sapi
            else -> R.drawable.kambing
        }
        binding.iv.setImageResource(resourceId)
        binding.tvHewan.text = hewan[index]
        when(index){
            0 -> {
                binding.btnLanjut.visibility = View.VISIBLE
                binding.btnKembali.visibility = View.GONE
            }
            4 -> {
                binding.btnLanjut.visibility = View.GONE
                binding.btnKembali.visibility = View.VISIBLE
            }
        }
    }

    private fun setupListeners() {
        binding.btnLanjut.setOnClickListener {
            showBtnNext()
        }
        binding.btnKembali.setOnClickListener{
            showBtnPrev()
        }

    }
}