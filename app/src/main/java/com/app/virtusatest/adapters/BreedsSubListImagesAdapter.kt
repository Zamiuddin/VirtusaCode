package com.app.virtusatest.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.virtusatest.R
import com.app.virtusatest.databinding.BreadsSubListImagesBinding
import com.bumptech.glide.Glide

class BreedsSubListImagesAdapter(
    private val context: Context, private val mList: ArrayList<String>
) : RecyclerView.Adapter<BreedsSubListImagesAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item

        val binding =
            BreadsSubListImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // binds the list items to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(mList[position]) {
                val breadsNameFromList = mList[position]
                Glide.with(context).load(breadsNameFromList)
                    .error(R.drawable.ic_launcher_background).into(binding.breadImages)
            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(val binding: BreadsSubListImagesBinding) :
        RecyclerView.ViewHolder(binding.root)
}