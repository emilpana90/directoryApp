package com.emilpana.directoryapp.ui.adapter

import android.content.Context
import android.text.Html
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
        val maxOccupancy: TextView
        val availability: TextView

        init {
            nameTextView = view.findViewById(R.id.name)
            maxOccupancy = view.findViewById(R.id.maxOccupancy)
            availability = view.findViewById(R.id.availability)
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
        holder.maxOccupancy.text =
            Html.fromHtml(context.getString(R.string.max_occcupancy, roomData.maxOccupancy))
        holder.availability.text = context.getString(
            if (roomData.isOccupied) {
                R.string.busy
            } else {
                R.string.available
            }
        )
        holder.availability.setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            if (roomData.isOccupied) {
                R.drawable.ic_event_busy
            } else {
                R.drawable.ic_event_available
            },
            0
        )
    }

    override fun getItemCount(): Int {
        return roomsList.size
    }
}
