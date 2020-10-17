package com.bilgiyon.pttemfilmuygulamasi.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bilgiyon.pttemfilmuygulamasi.R
import com.bilgiyon.pttemfilmuygulamasi.common.BaseActivity
import com.bilgiyon.pttemfilmuygulamasi.common.DetailTabPagerAdapter
import com.bilgiyon.pttemfilmuygulamasi.databinding.ActivityDetailBinding
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults
import com.bilgiyon.pttemfilmuygulamasi.util.Constants
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    private var movie: MovieResults? = null
    private var isFav: Boolean? = null

    override fun getLayoutRes(): Int = R.layout.activity_detail

    override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()

        intent.extras.let {
            movie = it?.getParcelable(Constants.EXTRA_POPULAR_MOVIES)
            setupViewPager(movie)
            fabBehaviour(movie)
            checkFav()
            btnfav.setOnClickListener { favorite() }
            detail_tabs.setupWithViewPager(detail_viewpager)
            dataBinding.movie = movie
        }
    }

    private fun setupUI() {
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupViewPager(movie: MovieResults?) {
        val adapter = DetailTabPagerAdapter(supportFragmentManager, movie)
        detail_viewpager.adapter = adapter
    }

    private fun fabBehaviour(movie: MovieResults?) {
        detail_appbarlayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                btnfav.hide()
                supportActionBar?.setDisplayShowTitleEnabled(true)
                detail_toolbar.title = this.movie?.title
            } else {
                btnfav.show()
                supportActionBar?.setDisplayShowTitleEnabled(false)
                detail_toolbar.title = " "
            }
        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            detail_collapsing_toolbarlayout.setExpandedTitleColor(
                resources.getColor(
                    android.R.color.transparent,
                    null
                )
            )
        } else {
            detail_collapsing_toolbarlayout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
        }
    }

    private fun checkFav() {
        viewModel.getSingleMovie(movie?.movieId).observe(this, Observer {
            if (it != null) {
                btnfav.setImageDrawable(resources.getDrawable(R.drawable.ic_fav, null))
                isFav = true
            } else {
                btnfav.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border, null))
                isFav = false
            }
        })
    }

    private fun favorite() {
        if (isFav!!) {
            viewModel.deleteMovie(movie)
        } else {
            viewModel.insertMovie(movie)
        }
    }
}