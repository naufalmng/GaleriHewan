package org.d3if2146.galerihewan.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import org.d3if2146.galerihewan.R
import org.d3if2146.galerihewan.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        with(binding){
            ivHewan.setImageResource(args.data.imgResId)
            tvNamaHewan.text = getString(R.string.nama_hewan_arg,args.data.nama)
            tvNamaLatin.text = getString(R.string.nama_latin_arg,args.data.namaLatin)
            tvJenisHewan.text = getString(R.string.jenis_hewan_arg,args.data.jenisHewan)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}