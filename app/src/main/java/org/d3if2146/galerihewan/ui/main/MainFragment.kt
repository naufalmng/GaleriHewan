package org.d3if2146.galerihewan.ui.main

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.d3if2146.galerihewan.adapter.MainAdapter
import org.d3if2146.galerihewan.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    companion object {
        const val  NAMA_HEWAN = "org.d3if2146.galerihewan.namaHewan"
        const val  NAMA_LATIN_HEWAN = "org.d3if2146.galerihewan.namaLatin"
        const val  JENIS_HEWAN = "org.d3if2146.galerihewan.jenisHewan"
        const val  FOTO_HEWAN = "org.d3if2146.galerihewan.fotoHewan"
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by lazy{
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var mainAdapter: MainAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        with(binding.recyclerView){
            adapter = mainAdapter
            addItemDecoration(DividerItemDecoration(context,RecyclerView.VERTICAL))
            setHasFixedSize(true)
            isNestedScrollingEnabled = true
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainAdapter = MainAdapter()
        setupSearchViewFilter()
        updateRecyclerViewItem()
        setupObservers()

//        onHewanItemClick()
     }

    private fun setupObservers() {
        mainViewModel.getData().observe(viewLifecycleOwner){
            mainAdapter.updateData(it)
        }
    }

    private fun updateRecyclerViewItem() {

    }


    private fun setupSearchViewFilter() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainAdapter.filter.filter(newText)
                return false
            }

        })
    }

    private fun onHewanItemClick() {
        mainAdapter.onItemClick = {
                data ->
            Toast.makeText(requireContext(), data.nama, Toast.LENGTH_SHORT).show()
//            val intent = Intent(requireActivity(), DetailActivity::class.java)
//            intent.putExtra(NAMA_HEWAN,data.nama)
//            intent.putExtra(NAMA_LATIN_HEWAN,data.namaLatin)
//            intent.putExtra(JENIS_HEWAN,data.jenisHewan)
//            intent.putExtra(FOTO_HEWAN,data.imgResId)
//            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}