package com.ricat.learn.android_beginner.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    var name: String,
    var distance: String,
    var location: String,
    var rating: Float,
    var totalReviews: Int,
    var description: String,
    var imageUrl: String,
    var priceRange: String
): Parcelable
