package com.app.virtusatest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.virtusatest.databinding.BreadsListBinding
import com.app.virtusatest.interfaces.AdapterClickListeners

class BreedsListAdapter(
    private val mList: ArrayList<String>, private val adapterClickListeners: AdapterClickListeners
) : RecyclerView.Adapter<BreedsListAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val binding = BreadsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    // binds the list items to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(mList[position]) {
                val breadsNameFromList = mList[position]
                binding.breadsName.text = breadsNameFromList
                binding.breadsName.setOnClickListener {
                    adapterClickListeners.onClick(breadsNameFromList)
                }
            }
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(val binding: BreadsListBinding) : RecyclerView.ViewHolder(binding.root)
}