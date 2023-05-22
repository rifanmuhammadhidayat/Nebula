package org.d3if3084.nebula.ui.history

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import org.d3if3084.nebula.R
import org.d3if3084.nebula.data.SettingDataStore
import org.d3if3084.nebula.data.dataStore
import org.d3if3084.nebula.databinding.FragmentHistoryBinding
import org.d3if3084.nebula.db.ZodiacDb

class HistoryFragment : Fragment() {
    private val layoutDataStore: SettingDataStore by lazy {
        SettingDataStore(requireContext().dataStore)
    }
    private var isLinearLayout = true
    private val viewModel: HistoryViewModel by lazy {
        val db = ZodiacDb.getInstance(requireContext())
        val factory = HistoryViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoryViewModel::class.java]
    }

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var myAdapter: HistoryAdapter


    private fun setLayout() {
        binding.recyclerView.layoutManager = if (isLinearLayout)
            LinearLayoutManager(context)
        else
            GridLayoutManager(context, 2)
    }
    private fun setIcon(menuItem: MenuItem) {
        val iconId = if (isLinearLayout)
            R.drawable.baseline_grid_view_24
        else
            R.drawable.baseline_view_list_24
        menuItem.icon = ContextCompat.getDrawable(requireContext(), iconId)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(
            layoutInflater,
            container, false
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = HistoryAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        layoutDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner) {
            isLinearLayout = it
            setLayout()
            activity?.invalidateOptionsMenu()
        }
        viewModel.data.observe(viewLifecycleOwner, {
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.history_menu, menu)
        val menuItem = menu.findItem(R.id.action_switch_layout)
        setIcon(menuItem)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllData()
            return true
        }

        if (item.itemId == R.id.action_switch_layout) {
            lifecycleScope.launch {
                layoutDataStore.saveLayout(!isLinearLayout, requireContext())
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteAllData() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.confirm_delete_all_history)
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                viewModel.deleteData()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

}
