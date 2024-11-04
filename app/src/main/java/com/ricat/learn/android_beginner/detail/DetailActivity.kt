package com.ricat.learn.android_beginner.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.ricat.learn.android_beginner.R
import com.ricat.learn.android_beginner.databinding.ActivityDetailBinding
import com.ricat.learn.android_beginner.home.Destination


class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding: ActivityDetailBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setViews()
        setListeners()
    }

    private fun setViews(){
        val dataDestination = intent.getParcelableExtra<Destination>(EXTRA_DESTINATION)
        binding.destinationImg.load(dataDestination?.imageUrl)
        binding.destinationName.text = dataDestination?.name
        binding.destinationLocation.text = getString(R.string.destination_location, dataDestination?.location)
        binding.destinationDistance.text = dataDestination?.distance
        binding.destinationRating.text = getString(R.string.destination_rating, dataDestination?.rating.toString())
        binding.destinationTotalReviews.text = getString(R.string.destination_reviews, dataDestination?.totalReviews.toString())
        binding.destinationTotalReviews.paintFlags = binding.destinationTotalReviews.paintFlags or android.graphics.Paint.UNDERLINE_TEXT_FLAG
        binding.destinationDesc.text = dataDestination?.description
        binding.priceRange.text = dataDestination?.priceRange
    }

    private fun setListeners(){
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.actionShare.setOnClickListener {
            shareDestination()
        }
        binding.btnSearch.setOnClickListener {
            openGoogle()
        }
    }

    private fun shareDestination() {
        val shareIntent = Intent()
        shareIntent.setAction(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Yuk, liburan ke ${binding.destinationName.text}!")
        shareIntent.setType("text/plain")
        startActivity(shareIntent)
    }

    private fun openGoogle(){
        val googleIntent = Intent()
        googleIntent.setAction(Intent.ACTION_VIEW)
        googleIntent.setData(Uri.parse("https://www.google.com/search?q=${binding.destinationName.text}"))
        startActivity(googleIntent)
    }

    companion object {
        const val EXTRA_DESTINATION = "extra_destination"
    }
}