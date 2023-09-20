package com.example.movie.ui.detail.detailPageRvAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.movieModel.Movie
import com.example.movie.databinding.DetailPageCastItemBinding
import com.example.movie.databinding.DetailpageRvMovieItemBinding
import com.example.movie.ui.detail.DetailPageFragmentDirections
import com.example.movie.utils.Constants
import com.squareup.picasso.Picasso

class DetailPageCastAdapter(private val movieModel: List<Movie>, private val navController: NavController) : RecyclerView.Adapter<DetailPageCastAdapter.MovieViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_page_cast_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val castName = movieModel[position].title
        val imageUrl = movieModel[position].posterPath



        Picasso.get().load(Constants.posterBaseUrl+imageUrl).placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image).into(holder.binding.imageView4)

       // holder.itemView.setOnClickListener { navController.navigate(DetailPageFragmentDirections.actionDetailPageFragmentSelf(movieModel[position].title, movieModel[position].posterPath, movieModel[position].overview,movieModel[position].id)) }

    }

    override fun getItemCount() = minOf(movieModel.size, 18)

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DetailPageCastItemBinding.bind(itemView)
    }

}

