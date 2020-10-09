package com.bilgiyon.pttemfilmuygulamasi.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bilgiyon.pttemfilmuygulamasi.ui.favorites.FavoritesFragment
import com.bilgiyon.pttemfilmuygulamasi.ui.moviegallery.MovieFragment

private const val TOTAL_TAB = 2

class TabAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MovieFragment()

            1 -> return FavoritesFragment()

            else -> return MovieFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val result: CharSequence
        when (position) {
            0 -> {
                result = "Filmler"
            }
            1 -> {
                result = "Favoriler"
            }
            else -> result = "Filmler"

        }
        return result
    }

    override fun getCount(): Int {
        return TOTAL_TAB
    }
}