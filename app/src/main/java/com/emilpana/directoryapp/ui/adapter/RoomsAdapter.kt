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
import com.emilpana.directoryapp.domain.entity.model.Room
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class RoomsAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView

        init {
            // View references
            nameTextView = view.findViewById(R.id.name)
        }
    }

    private val roomsList = ArrayList<Room>()

    fun setData(newList: List<Room>?) {
        roomsList.clear()
        newList?.let {
            roomsList.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val roomData = roomsList[position]
        holder.nameTextView.text = roomData.name
    }

    override fun getItemCount(): Int {
        return roomsList.size
    }
}
