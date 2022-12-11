package com.feritkeskin.pixabayjetpack.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.feritkeskin.pixabayjetpack.R
import com.feritkeskin.pixabayjetpack.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        navController = navHostFragment?.findNavController()
        navController?.let {
            binding.bottomNavigationView.setupWithNavController(it)
        }

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> show()
                R.id.detailFragment -> hide()
                R.id.favoriteFragment -> show()
            }
        }
    }

    private fun show() {
        binding.bottomNavigationView.visibility = View.VISIBLE
        binding.toolBar.visibility = View.GONE
    }

    private fun hide() {
        binding.bottomNavigationView.visibility = View.GONE
        binding.toolBar.visibility = View.VISIBLE
        binding.toolBar.setNavigationIcon(R.drawable.ic_back_black)
        binding.toolBar.title = "Detail Screen"
        binding.toolBar.setNavigationOnClickListener {
            Toast.makeText(this, "Click item", Toast.LENGTH_SHORT).show()
            navController?.popBackStack()
        }
    }
}