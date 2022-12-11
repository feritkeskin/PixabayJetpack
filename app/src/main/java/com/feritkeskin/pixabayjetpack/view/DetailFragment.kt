package com.feritkeskin.pixabayjetpack.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.feritkeskin.pixabayjetpack.databinding.FragmentDetailBinding
import com.feritkeskin.pixabayjetpack.util.loadUrl

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val action = DetailFragmentArgs.fromBundle(it).hit
            binding.userTv.text = action?.user
            binding.tagsTv.text = "Tags: #" + action?.tags?.replace(", ", " #")
            binding.likesTv.text = action?.likes.toString()

            //AFTER
            binding.detailImage.loadUrl(action?.previewURL.orEmpty())
            binding.userlImage.loadUrl(action?.userImageURL.orEmpty())
        }
    }
}