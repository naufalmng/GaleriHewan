package org.d3if2146.galerihewan.model

import androidx.annotation.Keep
import org.d3if2146.galerihewan.R
import java.io.Serializable
@Keep
data class Hewan(
    val nama: String,
    val namaLatin: String,
    val jenisHewan: String,
    val imgResId: Int
): Serializable