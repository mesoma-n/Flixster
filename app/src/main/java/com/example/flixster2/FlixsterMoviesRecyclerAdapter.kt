package com.example.flixster2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FlixsterMoviesRecyclerViewAdapter (
    private val movies: List<FlixsterMovie>,
    private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<FlixsterMoviesRecyclerViewAdapter.MovieViewHolder>()
{

    class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: FlixsterMovie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.movieTitle) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(R.id.movieDsc) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(R.id.movieImg) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.description

        val loader = Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + movie.movieImageUrl)
            .placeholder(R.drawable.mv_placeholder)
            .centerInside()
            .into(holder.mMovieImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

}