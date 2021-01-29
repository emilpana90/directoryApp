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
import androidx.recyclerview.widget.GridLayoutManager
import com.emilpana.directoryapp.R
import com.emilpana.directoryapp.databinding.FragmentPeopleBinding
import com.emilpana.directoryapp.presentation.people.PeopleViewModel
import com.emilpana.directoryapp.ui.adapter.PersonsAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    @Inject
    lateinit var adapter: PersonsAdapter
    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
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

        // Set up navigation and toolbar
        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)

        // Set up recycler view
        binding.recyclerView.layoutManager =
            GridLayoutManager(view.context, resources.getInteger(R.integer.columns))
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter.itemClickListener = {
            navController.navigate(
                PeopleFragmentDirections.actionPeopleFragmentToContactDetailsFragment(
                    it.id, getString(R.string.person_name, it.firstName, it.lastName)
                )
            )
        }
        binding.recyclerView.adapter = adapter

        binding.peopleProgressBar.isVisible = true
        binding.contentPlaceholder.root.isVisible = false
        binding.recyclerView.isVisible = true

        val viewModel: PeopleViewModel by viewModels()
        viewModel.peopleList.observe(
            viewLifecycleOwner,
            Observer { (personsList, error, isDataOld) ->
                binding.peopleProgressBar.isVisible = false
                binding.swipeRefresh.isRefreshing = false
                binding.oldDataBanner.root.isVisible =
                    isDataOld// && !binding.contentPlaceholder.root.isVisible

                if (error == null) {
                    // Hide the error
                    binding.contentPlaceholder.root.isVisible = false
                    binding.recyclerView.isVisible = true

                    // Update the list
                    adapter.setData(personsList)
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
