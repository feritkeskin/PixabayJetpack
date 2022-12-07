package com.feritkeskin.pixabayjetpack.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.feritkeskin.pixabayjetpack.R
import com.feritkeskin.pixabayjetpack.viewmodel.PixabayViewModel

class MainActivity : AppCompatActivity() {

    private var model = PixabayViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProvider(this).get(PixabayViewModel::class.java)
        model.getData("Mona")
        observers()
    }

    private fun observers() {
        model.image.observe(this) { model ->
            model.hits.forEach {  hit ->
                println("Hello this is pixabay: ${hit.tags}")
            }
        }
    }
}