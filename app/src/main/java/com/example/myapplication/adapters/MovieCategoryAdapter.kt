package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.UpcomingMoviesModel
import com.example.myapplication.R

class MovieCategoryAdapter(private val list: List<UpcomingMoviesModel.Result>): RecyclerView.Adapter<MovieCategoryAdapter.MovieCategoryHolder>() {

    var onClick: OnClickListener? = null

    interface OnClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_category_movie_list_view, parent, false)
        val holder = MovieCategoryHolder(view)
        holder.itemView.setOnClickListener {
            if (onClick != null) {
                val position = holder.adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    onClick!!.onItemClick(position)
                }
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MovieCategoryHolder, position: Int) {
        holder.name.text = list[position].getOriginal_title()
        holder.releaseDate.text = "Release Date \n"+list[position].getRelease_date()
        holder.desc.text = list[position].getOverview()
    }

    override fun getItemCount() = list.size

    class MovieCategoryHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.name)
        val desc: TextView = itemView.findViewById(R.id.desc)
        val releaseDate: TextView = itemView.findViewById(R.id.releaseDate)

    }
}