package com.app.virtusatest.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.virtusatest.R
import com.bumptech.glide.Glide

class BreedsSubListImagesAdapter(
    private val context: Context,
    private val mList: ArrayList<String>
) :
    RecyclerView.Adapter<BreedsSubListImagesAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.breads_sub_list_images, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val breadsNameFromList = mList[position]
        Glide.with(context)
            .load(breadsNameFromList)
            .error(R.drawable.ic_launcher_background)
            .into(holder.breadImages)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val breadImages: ImageView = itemView.findViewById(R.id.breadImages)
    }
}