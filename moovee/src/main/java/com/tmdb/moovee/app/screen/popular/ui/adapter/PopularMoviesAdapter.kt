package com.tmdb.moovee.app.screen.popular.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import com.moovee.engine.extension.toTransitionGroup
import com.moovee.engine.ui.BaseViewHolder
import com.tmdb.moovee.app.screen.popular.data.local.entity.PopularMovie
import com.tmdb.moovee.app.screen.popular.ui.fragment.PopularMoviesFragmentDirections
import com.tmdb.moovee.databinding.ListItemPopularMoviesBinding

class PopularMoviesAdapter :
    PagingDataAdapter<PopularMovie, PopularMoviesAdapter.ViewHolder>(PopularMoviesDiffUtil) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindItem(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemPopularMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ListItemPopularMoviesBinding) :
        BaseViewHolder<PopularMovie>(binding.root) {
        override fun bindItem(item: PopularMovie?) {
            binding.movie = item
            binding.viewHolder = this
            onItemClick(item)
            binding.executePendingBindings()
        }

        private fun onItemClick(item: PopularMovie?) {
            binding.clickListener = View.OnClickListener {
                if (item != null) {
                    val destination =
                        PopularMoviesFragmentDirections
                            .navToItemDetailFragment(
                                item.poster,
                                item.title,
                                item.description
                            )
                    val extras = FragmentNavigatorExtras(
                        binding.movieTitle.toTransitionGroup(),
                        binding.itemImage.toTransitionGroup()
                    )
                    it.findNavController().navigate(destination, extras)
                }
            }
        }
    }
}
