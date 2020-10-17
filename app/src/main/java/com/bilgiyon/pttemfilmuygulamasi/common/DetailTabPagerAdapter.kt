package com.bilgiyon.pttemfilmuygulamasi.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults
import com.bilgiyon.pttemfilmuygulamasi.ui.detail.overview.OverviewMoviesFragment
import com.bilgiyon.pttemfilmuygulamasi.ui.detail.review.ReviewMovieFragment

private const val TOTAL_TAB = 2

class DetailTabPagerAdapter(
    fm: FragmentManager,
    movie: MovieResults?
) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    var movie: MovieResults? = movie
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return OverviewMoviesFragment.newInstance(movie)

            1 -> return ReviewMovieFragment.newInstance(movie)

            else -> return OverviewMoviesFragment.newInstance(movie)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val result: CharSequence
        when (position) {
            0 -> {
                result = "Overview"
            }
            1 -> {
                result = "Review"
            }
            else -> result = "Overview"

        }
        return result
    }

    override fun getCount(): Int {
        return TOTAL_TAB
    }
}