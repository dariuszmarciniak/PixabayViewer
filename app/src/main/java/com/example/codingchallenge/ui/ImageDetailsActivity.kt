package com.example.codingchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.codingchallenge.model.Image
import com.example.codingchallenge.R

class ImageDetailsActivity : AppCompatActivity() {

    private lateinit var image: Image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        image = intent.getParcelableExtra("image") ?: return
        displayImageDetails()
    }

    private fun displayImageDetails() {
        findViewById<ImageView>(R.id.imageView).load(image.webformatURL)
        findViewById<TextView>(R.id.textViewUsername).text = image.user
        findViewById<TextView>(R.id.textViewTags).text = image.tags
        findViewById<TextView>(R.id.textViewLikes).text =  image.likes.toString()
        findViewById<TextView>(R.id.textViewDownloads).text =image.downloads.toString()
        findViewById<TextView>(R.id.textViewComments).text =  image.comments.toString()
    }
}
