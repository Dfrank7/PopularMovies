package com.francis.popularmovies.home.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.francis.popularmovies.home.model.Movie
import com.francis.popularmovies.databinding.MoviesItemBinding
import com.francis.popularmovies.utility.loadPicture

class MovieListAdapter(private val clickListener: MovieListener): ListAdapter<Movie, RecyclerView.ViewHolder>(ResultDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder-> {
                val result = getItem(position)
                holder.bind(result, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ResultDiffCallback : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder private constructor(private val binding: MoviesItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(result: Movie, clickListener: MovieListener){
            loadPicture(this.itemView, result.poster_path, binding.image)
            binding.tvOverview.text = result.overview
            binding.tvTitle.text = result.title
            binding.view.setOnClickListener {
                clickListener.onClick(result)
            }
            if (result.vote_average > 8.0) binding.ivStar.visibility = View.VISIBLE
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MoviesItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class MovieListener(val clickListener: (movieId: Movie) -> Unit){
        fun onClick(movie: Movie) = clickListener(movie)
    }


}