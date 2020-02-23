package com.okta.moviecatalogue.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okta.moviecatalogue.R
import com.okta.moviecatalogue.model.tvshowlist.ResultListTvShow

class RVTvShowAdapter(private val list: List<ResultListTvShow>) : RecyclerView.Adapter<RVTvShowAdapter.ListTvShowHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListTvShowHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
        return ListTvShowHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListTvShowHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ListTvShowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtName: TextView = itemView.findViewById(R.id.txt_name)
        private val txtDescription: TextView = itemView.findViewById(R.id.txt_description)
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_photo)
        fun bind(tvShow: ResultListTvShow) {
            txtName.text = tvShow.name
            txtDescription.text = tvShow.overview
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w185"+tvShow.posterPath)
                .into(imgPhoto)

            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(tvShow)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ResultListTvShow)
    }
}