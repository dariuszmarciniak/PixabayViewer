package com.example.codingchallenge.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.model.Image
import com.example.codingchallenge.adapter.ImageAdapter
import com.example.codingchallenge.R
import com.example.codingchallenge.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity(), ImageAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter
    private val apiKey = "37260810-b174bb00189b96fb54c58bad1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchText = findViewById<EditText>(R.id.searchText)
        val searchButton = findViewById<Button>(R.id.searchButton)
        recyclerView = findViewById<RecyclerView>(R.id.imagesList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        var query = "fruits"
        searchImages(query)

        searchButton.setOnClickListener {
            query = searchText.text.toString()
            searchImages(query)
        }

    }

    private fun searchImages(query: String) {
        runBlocking {
            val response = withContext(Dispatchers.IO) {
                RetrofitService.service.searchImages(apiKey, query)
            }

            if (response.hits != null && response.hits.isNotEmpty() ) {
                val images = response.hits
                adapter = ImageAdapter(images, this@SearchActivity)
                recyclerView.adapter = adapter
            } else {
                Toast.makeText(this@SearchActivity, "Brak wyników dla podanych tagów", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onItemClick(image: Image) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Do you wanna se more details?")
            .setPositiveButton("Yes") { _, _ ->
                val intent = Intent(this, ImageDetailsActivity::class.java)
                intent.putExtra("image", image)
                startActivity(intent)
            }
            .setNegativeButton("No", null)
            .create()
        dialog.show()
    }
}