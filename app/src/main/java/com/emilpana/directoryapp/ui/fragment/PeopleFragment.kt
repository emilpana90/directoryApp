package com.emilpana.directoryapp.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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

        // Set up details fragment
        val showContactDetails = resources.getBoolean(R.bool.showDetails)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.contactDetailsFragmentContainer) as NavHostFragment
        val secondNavController = navHostFragment.navController

        // Set up recycler view
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            )
        )

        val viewModel: PeopleViewModel by viewModels()

        if (showContactDetails) {
            // Show person contact details fragment
            childFragmentManager.beginTransaction().show(navHostFragment).commit()

            viewModel.selectedItemId?.let {
                val args = bundleOf("personId" to it, "blockHide" to true)
                secondNavController.navigate(R.id.contactDetailsFragment2, args)
            }
        } else {
            // Hide contact details
            childFragmentManager.beginTransaction().hide(navHostFragment).commit()
        }

        adapter.itemClickListener = {
            // Save id in view model to use it in case of device rotation
            viewModel.selectedItemId = it.id

            if (showContactDetails) {
                // Show person details in same screen
                val args = bundleOf("personId" to it.id, "blockHide" to true)
                secondNavController.navigate(R.id.contactDetailsFragment2, args)
            } else {
                // Show person details in another screen
                navController.navigate(
                    PeopleFragmentDirections.actionPeopleFragmentToContactDetailsFragment(
                        it.id, getString(R.string.person_name, it.firstName, it.lastName)
                    )
                )
            }
        }
        binding.recyclerView.adapter = adapter

        binding.peopleProgressBar.isVisible = true
        binding.contentPlaceholder.root.isVisible = false
        binding.recyclerView.isVisible = true

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
