import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.movieModel.Movie
import com.example.movie.databinding.HomepageRvMovieItemBinding
import com.example.movie.utils.Constants
import com.squareup.picasso.Picasso

class MovieAdapter(private val movieModel: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.homepage_rv_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val imageUrl = movieModel[position].posterPath
        val movieTitle = movieModel[position].originalTitle
        val voteAverage = movieModel[position].voteAverage.toString()

        val trimmedText = if (movieTitle.length > 40) {
            movieTitle.substring(0, 25) + "..."
        } else {
            movieTitle
        }
        holder.binding.textViewMovieName.text =  trimmedText
        holder.binding.textView2.text =  voteAverage

        Picasso.get().load(Constants.POSTER_BASE_URL+imageUrl).into(holder.binding.imageView4)
    }

    override fun getItemCount() = movieModel.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = HomepageRvMovieItemBinding.bind(itemView)
    }
}