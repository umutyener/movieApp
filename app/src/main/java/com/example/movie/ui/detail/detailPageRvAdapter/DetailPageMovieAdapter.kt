package com.example.movie.ui.detail.detailPageRvAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.movieModel.Movie
import com.example.movie.databinding.DetailpageRvMovieItemBinding
import com.example.movie.ui.detail.DetailPageFragmentDirections
import com.example.movie.ui.home.HomeScreenViewPagerFragmentDirections
import com.example.movie.utils.Constants
import com.squareup.picasso.Picasso

class DetailPageMovieAdapter (private val movieModel: List<Movie>, private val navController: NavController) : RecyclerView.Adapter<DetailPageMovieAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detailpage_rv_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movieTitle = movieModel[position].originalTitle
        val voteAverage = movieModel[position].voteAverage.toString()
        val imageUrl = movieModel[position].posterPath

        val trimmedText = if (movieTitle.length > 40) movieTitle.substring(0, 25) + "..." else movieTitle

        holder.binding.textViewMovieName.text =  trimmedText
        holder.binding.textView2.text =  voteAverage

        Picasso.get().load(Constants.posterBaseUrl+imageUrl).into(holder.binding.imageView4)

        holder.itemView.setOnClickListener {
            navController.navigate(DetailPageFragmentDirections.actionDetailPageFragmentSelf(movieModel[position].title, movieModel[position].posterPath, movieModel[position].overview,movieModel[position].id))
        }

    }

    override fun getItemCount() = minOf(movieModel.size, 18)

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DetailpageRvMovieItemBinding.bind(itemView)
    }

}

