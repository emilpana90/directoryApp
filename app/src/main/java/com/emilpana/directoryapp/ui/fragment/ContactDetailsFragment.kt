package com.emilpana.directoryapp.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.emilpana.directoryapp.R
import com.emilpana.directoryapp.databinding.FragmentContactDetailsBinding
import com.emilpana.directoryapp.presentation.people.ContactDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactDetailsFragment : Fragment() {

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!
    val args: ContactDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up navigation and toolbar
        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)

        binding.contentPlaceholder.root.isVisible = false
        binding.progressBar.isVisible = true

        val viewModel: ContactDetailsViewModel by viewModels()
        viewModel.personLiveData.observe(viewLifecycleOwner, { (personData, error) ->
            binding.progressBar.isVisible = false

            if (error == null) {
                // Hide the error
                binding.contentPlaceholder.root.isVisible = false

                personData?.let { person ->
                    // Load avatar image
                    Glide.with(view.context)
                        .load(person.avatar)
                        .placeholder(R.drawable.ic_person)
                        .into(binding.avatar)

                    binding.name.text =
                        getString(R.string.person_name, person.firstName, person.lastName)

                    // Setting the person's favorite color
                    personData.favouriteColor?.let { color ->
                        // Reduce the intensity of the color before setting as background
                        arrayOf(binding.colorBar, binding.colorBar2).forEach {
                            it.setBackgroundColor(
                                Color.parseColor(
                                    color.replace(
                                        "#",
                                        "#C0"
                                    )
                                )
                            )
                        }
                    }

                    binding.jobTitle.text = person.jobTitle
                    binding.email.text = person.email
                    binding.phone.text = person.phone
                    binding.location.setOnClickListener {
                        val uri =
                            Uri.parse("geo:${person.latitude},${person.longitude}?q=${person.latitude},${person.longitude}")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        val chooser = Intent.createChooser(intent, "")
                        try {
                            startActivity(chooser)
                        } catch (e: ActivityNotFoundException) {
                            // No activity found to open maps with
                        }
                    }
                }
            } else {
                // Handle the error
                binding.contentPlaceholder.root.isVisible = true
                when (error) {
                    else -> {
                        binding.contentPlaceholder.title.setText(R.string.error_generic_title)
                        binding.contentPlaceholder.subtitle.setText(R.string.error_generic_subtitle)
                    }
                }
            }
        })
        viewModel.setPersonId(args.personId)
    }
}
