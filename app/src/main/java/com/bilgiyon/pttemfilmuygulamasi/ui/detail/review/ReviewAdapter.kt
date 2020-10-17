package com.bilgiyon.pttemfilmuygulamasi.ui.detail.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bilgiyon.pttemfilmuygulamasi.databinding.ItemReviewBinding
import com.bilgiyon.pttemfilmuygulamasi.model.reviews.MovieReviewResult

class ReviewAdapter : ListAdapter<MovieReviewResult, ReviewAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
                val itemReviewBinding: ItemReviewBinding =
                    ItemReviewBinding.inflate(inflater, parent, false)
                return ViewHolder(itemReviewBinding)
            }
        }

        fun bind(movieReviewResult: MovieReviewResult) {
            binding.review = movieReviewResult
            binding.executePendingBindings()
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieReviewResult> = object :
            DiffUtil.ItemCallback<MovieReviewResult>() {
            override fun areItemsTheSame(
                oldItem: MovieReviewResult,
                newItem: MovieReviewResult
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieReviewResult,
                newItem: MovieReviewResult
            ): Boolean = oldItem.content == newItem.content
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(
            LayoutInflater.from(parent.context), parent
        )
}