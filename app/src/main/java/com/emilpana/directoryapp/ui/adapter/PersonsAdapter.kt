/*
 * Game of Pods - Copyright (c) Cognizant Softvision 2020.
 * All rights reserved.
 */
package com.emilpana.directoryapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emilpana.directoryapp.R
import com.emilpana.directoryapp.domain.entity.model.Person
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class PersonsAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<PersonsAdapter.PersonViewHolder>() {
    class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView

        init {
            // View references
            nameTextView = view.findViewById(R.id.name)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val personData = personsList[position]
        holder.nameTextView.text =
            context.getString(R.string.person_name, personData.firstName, personData.lastName)
    }

    override fun getItemCount(): Int {
        return personsList.size
    }
}
