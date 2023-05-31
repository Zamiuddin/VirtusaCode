package com.app.virtusatest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.virtusatest.R
import com.app.virtusatest.interfaces.AdapterClickListeners

class BreedsListAdapter(
    private val mList: ArrayList<String>,
    private val adapterClickListeners: AdapterClickListeners
) :
    RecyclerView.Adapter<BreedsListAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.breads_list, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val breadsNameFromList = mList[position]
        holder.breadsName.text = breadsNameFromList
        holder.breadsName.setOnClickListener {
            adapterClickListeners.onClick(breadsNameFromList)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val breadsName: TextView = itemView.findViewById(R.id.breadsName)
    }
}