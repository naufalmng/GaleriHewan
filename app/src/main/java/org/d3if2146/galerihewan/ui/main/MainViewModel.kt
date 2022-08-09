package org.d3if2146.galerihewan.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if2146.galerihewan.R
import org.d3if2146.galerihewan.model.Hewan

class MainViewModel : ViewModel() {

    private val data = MutableLiveData<ArrayList<Hewan>>()
    init {
        data.value = initDataHewan()
    }

    private fun initDataHewan(): ArrayList<Hewan> {
        val nama = arrayOf(
            "Angsa",
            "Ayam",
            "Bebek",
            "Domba",
            "Kalkun",
            "Kambing",
            "Kelinci",
            "Kerbau",
            "Kuda",
            "Sapi"
        )
        val namaLatin = arrayOf(
            "Cygnus olor",
            "Gallus gallus",
            "Cairina moschata",
            "Ovis ammon",
            "Meleagris gallopavo",
            "Capricornis sumatrensis",
            "Oryctolagus cuniculus",
            "Bubalus bubalis",
            "Equus caballus",
            "Bos taurus"
        )
        val jenisHewan = arrayOf(
            "Unggas",
            "Unggas",
            "Unggas",
            "Mammalia",
            "Unggas",
            "Mammalia",
            "Mammalia",
            "Mammalia",
            "Mammalia",
            "Mammalia",
        )
        val imgResId = arrayOf(
            R.drawable.angsa,
            R.drawable.ayam,
            R.drawable.bebek,
            R.drawable.domba,
            R.drawable.kalkun,
            R.drawable.kambing,
            R.drawable.kelinci,
            R.drawable.kerbau,
            R.drawable.kuda,
            R.drawable.sapi
        )

        val listDataHewan: ArrayList<Hewan> = arrayListOf()
        for (i in nama.indices){
            listDataHewan.add(Hewan(nama[i], namaLatin[i],jenisHewan[i], imgResId[i]))
        }
        return listDataHewan
    }

    fun getData(): LiveData<ArrayList<Hewan>> = data

}