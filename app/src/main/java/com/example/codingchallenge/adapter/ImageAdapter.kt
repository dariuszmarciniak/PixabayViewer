package com.example.codingchallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.codingchallenge.R
import com.example.codingchallenge.model.Image

class ImageAdapter(private val images: List<Image>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(image: Image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewThumbnail: ImageView = itemView.findViewById(R.id.imageViewThumbnail)
        private val textViewUsername: TextView = itemView.findViewById(R.id.textViewUsername)
        private val textViewTags: TextView = itemView.findViewById(R.id.textViewTags)

        fun bind(image: Image) {
            imageViewThumbnail.load(image.previewURL)
            textViewUsername.text = image.user
            textViewTags.text = image.tags
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedImage = images[position]
                    itemClickListener.onItemClick(clickedImage)
                }
            }
        }

    }
}