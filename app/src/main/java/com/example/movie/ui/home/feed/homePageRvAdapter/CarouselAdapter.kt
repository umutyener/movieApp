package com.example.movie.ui.home.feed.homePageRvAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.movieModel.Movie
import com.example.movie.utils.Constants
import com.squareup.picasso.Picasso


class CarouselAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.carousel_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movies[position]

        val imageUrl = Constants.backdropBaseUrl + movie.backdropPath

        holder.movieTitle.text = movie.title
        holder.movieDate.text = movie.releaseDate

        Picasso.get()
            .load(imageUrl)
            .into(holder.movieImage)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView = itemView.findViewById(R.id.textViewSliderTitle)
        val movieDate: TextView = itemView.findViewById(R.id.textViewSliderDate)
        val movieImage: ImageView = itemView.findViewById(R.id.imageView)

    }
}

