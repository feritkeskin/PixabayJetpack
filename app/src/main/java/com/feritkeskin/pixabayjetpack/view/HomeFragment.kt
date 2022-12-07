package com.feritkeskin.pixabayjetpack.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.feritkeskin.pixabayjetpack.adapter.HomeAdapter
import com.feritkeskin.pixabayjetpack.databinding.FragmentHomeBinding
import com.feritkeskin.pixabayjetpack.model.Hit
import com.feritkeskin.pixabayjetpack.viewmodel.PixabayViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var model = PixabayViewModel()
    private var histArrayList = ArrayList<Hit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(PixabayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        model.getData("")
        observers()
        return binding.root
    }

    private fun observers() {
        model.image.observe(viewLifecycleOwner) { model ->
            model.hits.forEach { hit ->
                histArrayList.add(hit)
                init()
            }
        }
    }

    private fun init() {
        binding.recyclerViewHome.layoutManager = GridLayoutManager(context, 2)
        val homeAdapter = HomeAdapter(histArrayList)
        binding.recyclerViewHome.adapter = homeAdapter
    }
}