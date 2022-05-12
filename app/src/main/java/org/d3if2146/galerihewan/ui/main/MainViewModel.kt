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
            "Sapi",
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

//        return listOf(
//            Hewan("Angsa","Cygnus olor","Unggas", R.drawable.angsa),
//            Hewan("Ayam","Gallus gallus","Unggas", R.drawable.ayam),
//            Hewan("Bebek","Cairina moschata","Unggas", R.drawable.bebek),
//            Hewan("Domba","Ovis ammon","Mammalia", R.drawable.domba),
//            Hewan("Kalkun","Meleagris gallopavo","Unggas", R.drawable.kalkun),
//            Hewan("Kambing","Capricornis sumatrensis","Mammalia", R.drawable.kambing),
//            Hewan("Kelinci","Oryctolagus cuniculus","Mammalia", R.drawable.kelinci),
//            Hewan("Kerbau","Bubalus bubalis","Mammalia", R.drawable.kerbau),
//            Hewan("Kuda","Equus caballus","Mammalia", R.drawable.kuda),
//            Hewan("Sapi","Bos taurus","Mammalia", R.drawable.sapi)
//        )

    }

    fun getData(): LiveData<ArrayList<Hewan>> = data

}