package com.example.movie.ui.detail.detailPageRvAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.model.movieModel.Cast
import com.example.movie.databinding.DetailPageCastItemBinding
import com.example.movie.utils.Constants
import com.squareup.picasso.Picasso
class DetailPageCastAdapter(private val castModel: List<Cast>, private val navController: NavController) : RecyclerView.Adapter<DetailPageCastAdapter.CastViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_page_cast_item, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {


        Log.e("CAST LIST",castModel[1].name)


        val castName = castModel[position].name
        val characterName = castModel[position].character
        val charaterGender = castModel[position].gender
        var placeHolderPhoto = R.drawable.cast_woman

        placeHolderPhoto = if (charaterGender == 1) R.drawable.cast_woman else R.drawable.cast_man //( I am not homophobic.)


        val imageUrl = castModel[position].profilePath
        holder.binding.textView12.text =  castName
        holder.binding.textView13.text =  characterName



        Picasso.get().load(Constants.posterBaseUrl+imageUrl).error(placeHolderPhoto).placeholder(placeHolderPhoto).into(holder.binding.imageView6)



    }

    override fun getItemCount() = castModel.size

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DetailPageCastItemBinding.bind(itemView)
    }

}

