package com.feritkeskin.pixabayjetpack.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.feritkeskin.pixabayjetpack.adapter.HomeAdapter
import com.feritkeskin.pixabayjetpack.databinding.FragmentHomeBinding
import com.feritkeskin.pixabayjetpack.model.Hit
import com.feritkeskin.pixabayjetpack.viewmodel.PixabayViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        listener()
        return binding.root
    }

    private fun listener() {
        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            //ANR -> Android Not Response
            job?.cancel()
            job = lifecycleScope.launch {
                binding.pbHome.visibility = View.VISIBLE
                binding.recyclerViewHome.visibility = View.GONE
                delay(1000)
                editable?.let {
                    if (it.toString().isNotEmpty()) {
                        model.getData(it.toString())
                    } else {
                        model.getData("")
                    }
                    observers()
                    binding.pbHome.visibility = View.GONE
                    binding.recyclerViewHome.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun observers() {
        model.image.observe(viewLifecycleOwner) { model ->
            histArrayList.clear()
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