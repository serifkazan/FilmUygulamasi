package com.bilgiyon.pttemfilmuygulamasi.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bilgiyon.pttemfilmuygulamasi.ui.main.favorites.FavoritesFragment
import com.bilgiyon.pttemfilmuygulamasi.ui.main.popular.PopularMoviesFragment
import com.bilgiyon.pttemfilmuygulamasi.ui.main.toprated.TopRatedFragment

private const val TOTAL_TAB = 3

class TabPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return PopularMoviesFragment()

            1 -> return TopRatedFragment()

            2 -> return FavoritesFragment()

            else -> return PopularMoviesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val result: CharSequence
        when (position) {
            0 -> {
                result = "Popular"
            }
            1 -> {
                result = "Top Rated"
            }
            2->{
                result = "Favorites"
            }
            else -> result = "Popular"

        }
        return result
    }

    override fun getCount(): Int {
        return TOTAL_TAB
    }
}