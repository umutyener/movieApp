import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.movieModel.TMDBResponse
import com.example.movie.databinding.HomepageRvParentItemBinding

class MainAdapter(private val collection: List<TMDBResponse>) : RecyclerView.Adapter<MainAdapter.CollectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.homepage_rv_parent_item, parent, false)
        return CollectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.binding.apply {
            val response = collection[position]
            val movieAdapter = MovieAdapter(response.results)
            rvMovieChild.adapter = movieAdapter
        }
    }


    override fun getItemCount() = collection.size

    inner class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = HomepageRvParentItemBinding.bind(itemView)
    }
}
