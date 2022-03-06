package org.d3if2146.galerihewan.model

import org.d3if2146.galerihewan.R

data class Hewan(
    val nama: String,
    val namaLatin: String,
    val jenisHewan: String,
    val imgResId: Int
)

fun getDataHewan(): List<Hewan>{
    return listOf(
        Hewan("Angsa","Cygnus olor","Unggas", R.drawable.angsa),
        Hewan("Ayam","Gallus gallus","Unggas", R.drawable.ayam),
        Hewan("Bebek","Cairina moschata","Unggas", R.drawable.bebek),
        Hewan("Domba","Ovis ammon","Mammalia", R.drawable.domba),
        Hewan("Kalkun","Meleagris gallopavo","Unggas", R.drawable.kalkun),
        Hewan("Kambing","Capricornis sumatrensis","Mammalia", R.drawable.kambing),
        Hewan("Kelinci","Oryctolagus cuniculus","Mammalia", R.drawable.kelinci),
        Hewan("Kerbau","Bubalus bubalis","Mammalia", R.drawable.kerbau),
        Hewan("Kuda","Equus caballus","Mammalia", R.drawable.kuda),
        Hewan("Sapi","Bos taurus","Mammalia", R.drawable.sapi)
    )
}