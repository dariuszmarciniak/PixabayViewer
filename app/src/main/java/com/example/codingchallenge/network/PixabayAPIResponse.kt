package com.example.codingchallenge.network

import com.example.codingchallenge.model.Image

data class PixabayAPIResponse(
    val hits: List<Image>?
    )

