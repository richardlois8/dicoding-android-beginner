package com.ricat.learn.android_beginner.home

data class Destination(
    var name: String,
    var distance: String,
    var location: String,
    var rating: Float,
    var totalReviews: Int,
    var description: String,
    var imageUrl: String,
    var priceRange: String
)
