/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emilpana.directoryapp.R
import com.emilpana.directoryapp.domain.entity.model.Person
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class PersonsAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<PersonsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarImageView: ImageView
        val nameTextView: TextView
        val jobTitleTextView: TextView

        init {
            // View references
            avatarImageView = view.findViewById(R.id.avatar)
            nameTextView = view.findViewById(R.id.name)
            jobTitleTextView = view.findViewById(R.id.jobTitle)
        }
    }

    private val personsList = ArrayList<Person>()

    fun setData(newList: List<Person>?) {
        personsList.clear()
        newList?.let {
            personsList.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personData = personsList[position]

        Glide.with(context)
            .load(personData.avatar)
//            .placeholder(R.drawable.placeholder) TODO
            .into(holder.avatarImageView)

        holder.nameTextView.text =
            context.getString(R.string.person_name, personData.firstName, personData.lastName)
        holder.jobTitleTextView.text = personData.jobTitle ?: ""
    }

    override fun getItemCount(): Int {
        return personsList.size
    }
}
