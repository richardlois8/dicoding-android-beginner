package com.ricat.learn.android_beginner.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ricat.learn.android_beginner.R
import com.ricat.learn.android_beginner.about.AboutActivity
import com.ricat.learn.android_beginner.databinding.ActivityMainBinding
import com.ricat.learn.android_beginner.databinding.ActivitySplashBinding
import com.ricat.learn.android_beginner.detail.DetailActivity

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initListDestinations()
        setListeners()
    }

    private fun initListDestinations(){
        val listDestinations = TravelDestinations.arrDestinations
        val adapter = DestinationAdapter(object : RvClickListener {
            override fun onItemClick(data: Destination) {
                val detailActivity = Intent(this@MainActivity, DetailActivity::class.java)
                detailActivity.putExtra(DetailActivity.EXTRA_DESTINATION, data)
                startActivity(detailActivity)
            }
        })
        adapter.setDestinations(listDestinations)
        binding.rvDestination.layoutManager = LinearLayoutManager(this)
        binding.rvDestination.adapter = adapter
    }

    private fun setListeners(){
        binding.aboutPage.setOnClickListener {
            val aboutActivity = Intent(this, AboutActivity::class.java)
            startActivity(aboutActivity)
        }
    }
}