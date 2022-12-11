package com.feritkeskin.pixabayjetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feritkeskin.pixabayjetpack.R
import com.feritkeskin.pixabayjetpack.model.Hit
import com.feritkeskin.pixabayjetpack.util.loadUrl

class HomeAdapter(
    private val rowList: ArrayList<Hit>,
    private val clickHitItem: (Hit) -> Unit
) : RecyclerView.Adapter<HomeAdapter.HomeAdapterViewHolder>() {

    class HomeAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var user: TextView

        init {
            image = view.findViewById(R.id.ivRowPreview)
            user = view.findViewById(R.id.tvRowUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.image_row, parent, false)
        return HomeAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        val list = rowList[position]
        holder.image.loadUrl(list.previewURL)
        holder.user.text = list.user
        holder.itemView.setOnClickListener {
            clickHitItem.invoke(list)
        }
    }

    override fun getItemCount(): Int {
        return rowList.size
    }
}