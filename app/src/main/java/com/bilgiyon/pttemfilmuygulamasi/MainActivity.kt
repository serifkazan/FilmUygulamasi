package com.bilgiyon.pttemfilmuygulamasi

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bilgiyon.pttemfilmuygulamasi.api.ApiCommand
import com.bilgiyon.pttemfilmuygulamasi.data.ConfigurationModel
import com.bilgiyon.pttemfilmuygulamasi.data.MovieRepository
import com.bilgiyon.pttemfilmuygulamasi.ui.TabAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    companion object {
        var configuration: ConfigurationModel? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout: TabLayout = findViewById(R.id.tablayout)
        val viewPager: ViewPager = findViewById(R.id.viewpager)
        viewPager.setAdapter(TabAdapter(supportFragmentManager))
        tabLayout.setupWithViewPager(viewPager)

        getConfiguration(this)
    }

    fun getConfiguration(context: Context) {
        val apiCommand = object : ApiCommand<ConfigurationModel?> {
            override fun onSuccess(response: ConfigurationModel?) {
                configuration = response
            }

            override fun onFailure(t: String) {
                Toast.makeText(context, t, Toast.LENGTH_LONG).show()
            }

        }

        val movieRepository = MovieRepository(this)
        movieRepository.getConfigurations(apiCommand)
    }
}