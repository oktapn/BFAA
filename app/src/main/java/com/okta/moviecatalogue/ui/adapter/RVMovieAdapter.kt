package com.okta.moviecatalogue.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okta.moviecatalogue.R
import com.okta.moviecatalogue.model.movielist.ResultListMovie

class RVMovieAdapter(private val list: List<ResultListMovie>) : RecyclerView.Adapter<RVMovieAdapter.ListMovieHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListMovieHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
        return ListMovieHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ListMovieHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ListMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtName: TextView = itemView.findViewById(R.id.txt_name)
        private val txtDescription: TextView = itemView.findViewById(R.id.txt_description)
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_photo)
        fun bind(movie: ResultListMovie) {
            txtName.text = movie.title
            txtDescription.text = movie.overview
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w185"+movie.posterPath)
                .into(imgPhoto)

            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(movie)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ResultListMovie)
    }
}