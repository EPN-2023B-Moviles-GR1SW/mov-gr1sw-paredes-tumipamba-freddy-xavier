package com.example.proyecoiib.utils


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.proyecoiib.R
import com.example.proyecoiib.model.Place


class PlaceAdapter(context: Context, places: ArrayList<Place>) :
    ArrayAdapter<Place>(context, 0, places) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val place = getItem(position)

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_custom_place, parent, false)
        }

        itemView!!.customNameTextView.text = place?.name
        itemView.customDescTextView.text = place?.description
        // Load image using a library like Picasso or Glide into customImageView

        return itemView
    }

}
