package org.d3if2146.galerihewan.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.d3if2146.galerihewan.R
import org.d3if2146.galerihewan.adapter.MainAdapter
import org.d3if2146.galerihewan.databinding.ActivityMainBinding
import org.d3if2146.galerihewan.databinding.FragmentMainBinding
import org.d3if2146.galerihewan.utils.DataStoreSettings

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainAdapter: MainAdapter
    private var isLinearLayoutManager = true
    private lateinit var layoutDataStore: DataStoreSettings

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainAdapter = MainAdapter()
        prepareRecyclerView()
     }

    @SuppressLint("NotifyDataSetChanged")
    private fun prepareRecyclerView() {
        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
        mainAdapter.notifyDataSetChanged()
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
    private fun setupLayoutSwitcher() {
        if(isLinearLayoutManager) binding.recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        else binding.recyclerView.layoutManager = GridLayoutManager(this.requireContext(),2)
    }

    private fun setLayoutSwitcherIcon(menuItem: MenuItem?){
        if(menuItem== null) return

        menuItem.icon = if(isLinearLayoutManager) ContextCompat.getDrawable(requireContext(),R.drawable.ic_linear_layout)
        else ContextCompat.getDrawable(requireContext(),R.drawable.ic_grid_layout)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}