package org.d3if2146.galerihewan.ui.main

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.d3if2146.galerihewan.R
import org.d3if2146.galerihewan.adapter.MainAdapter
import org.d3if2146.galerihewan.data.DataStoreSettings
import org.d3if2146.galerihewan.data.dataStore
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
    private var isLinearLayoutManager = true
    private lateinit var layoutDataStore: DataStoreSettings

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
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainAdapter = MainAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchViewFilter()
        updateRecyclerViewItem()
        setupObservers()
        setupLayoutDataStore()


//        onHewanItemClick()
     }

    private fun setupLayoutDataStore() {
        layoutDataStore = DataStoreSettings(requireContext().dataStore)
        layoutDataStore.preferenceFlow.asLiveData()
            .observe(viewLifecycleOwner){value->
                isLinearLayoutManager = value
                setupLayoutSwitcher()
                activity?.invalidateOptionsMenu()

            }
    }

    private fun setupLayoutSwitcher() {
        if(isLinearLayoutManager) binding.recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        else binding.recyclerView.layoutManager = GridLayoutManager(this.requireContext(),2)
    }

    private fun setLayoutSwitcherIcon(menuItem: MenuItem?){
        if(menuItem== null) return

        menuItem.icon = if(isLinearLayoutManager) ContextCompat.getDrawable(requireContext(),R.drawable.ic_linear_layout)
        else ContextCompat.getDrawable(requireContext(),R.drawable.ic_grid_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu,menu)
        val layoutBtn = menu.findItem(R.id.action_switch_layout)
        setLayoutSwitcherIcon(layoutBtn)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager

                lifecycleScope.launch {
                    layoutDataStore.saveLayoutToPreferencesStore(isLinearLayoutManager,requireContext())
                }

                setupLayoutSwitcher()
                setLayoutSwitcherIcon(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

//    private fun onHewanItemClick() {
//        mainAdapter.onItemClick = {
//                data ->
//            Toast.makeText(requireContext(), data.nama, Toast.LENGTH_SHORT).show()
////            val intent = Intent(requireActivity(), DetailActivity::class.java)
////            intent.putExtra(NAMA_HEWAN,data.nama)
////            intent.putExtra(NAMA_LATIN_HEWAN,data.namaLatin)
////            intent.putExtra(JENIS_HEWAN,data.jenisHewan)
////            intent.putExtra(FOTO_HEWAN,data.imgResId)
////            startActivity(intent)
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}