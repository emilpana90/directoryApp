package com.emilpana.directoryapp.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.emilpana.directoryapp.R
import com.emilpana.directoryapp.databinding.FragmentRoomsBinding
import com.emilpana.directoryapp.presentation.room.RoomsViewModel
import com.emilpana.directoryapp.ui.adapter.RoomsAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    @Inject
    lateinit var adapter: RoomsAdapter
    private var _binding: FragmentRoomsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.refresh_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
//            R.id.menu_refresh -> {
//                binding.swipeRefresh.isRefreshing = true
//                // viewModel.refreshData() TODO
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)

        // Set up recycler view
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = adapter

        binding.progressBar.isVisible = true
        binding.contentPlaceholder.root.isVisible = false
        binding.recyclerView.isVisible = true

        val viewModel: RoomsViewModel by viewModels()
        viewModel.roomList.observe(viewLifecycleOwner, Observer { (roomList, error) ->
            binding.progressBar.isVisible = false
            binding.swipeRefresh.isRefreshing = false
            if (error == null) {
                // Hide the error & show recycler view
                binding.contentPlaceholder.root.isVisible = false
                binding.recyclerView.isVisible = true

                // Update the list
                adapter.setData(roomList)
            } else {
                // Handle the error
                binding.contentPlaceholder.root.isVisible = true
                binding.recyclerView.isVisible = false
                when (error) {
                    is IOException -> {
                        binding.contentPlaceholder.title.setText(R.string.error_no_internet_title)
                        binding.contentPlaceholder.subtitle.setText(R.string.error_no_internet_subtitle)
                    }
                    else -> {
                        binding.contentPlaceholder.title.setText(R.string.error_generic_title)
                        binding.contentPlaceholder.subtitle.setText(R.string.error_generic_subtitle)
                    }
                }
            }
        })
        viewModel.refreshData()

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
