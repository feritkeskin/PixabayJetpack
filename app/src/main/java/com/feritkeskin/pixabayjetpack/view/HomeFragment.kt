package com.feritkeskin.pixabayjetpack.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
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
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        model.getData("")
        observers()
        listener()
    }

    private fun listener() {
        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            //ANR -> Android Not Response
            job?.cancel()
            job = lifecycleScope.launch {
                shimmerStart()
                binding.recyclerViewHome.visibility = View.GONE
                delay(1000)
                editable?.let {
                    if (it.toString().isNotEmpty()) {
                        model.getData(it.toString())
                    } else {
                        model.getData("")
                    }
                    observers()
                    shimmerStop()
                    binding.recyclerViewHome.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun shimmerStart() {
        binding.shimmerViewContainer.startShimmerAnimation()
        binding.shimmerViewContainer.visibility = View.VISIBLE
    }

    private fun shimmerStop() {
        binding.shimmerViewContainer.stopShimmerAnimation()
        binding.shimmerViewContainer.visibility = View.GONE
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
        if (histArrayList.size > 0) {
            shimmerStop()
        }
        val homeAdapter = HomeAdapter(histArrayList) { hit ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(hit)
            view?.findNavController()?.navigate(action)
        }
        binding.recyclerViewHome.adapter = homeAdapter
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerViewContainer.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerViewContainer.stopShimmerAnimation()
    }
}