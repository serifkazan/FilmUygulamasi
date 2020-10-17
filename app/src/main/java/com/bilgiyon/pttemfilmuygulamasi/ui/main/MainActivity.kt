package com.bilgiyon.pttemfilmuygulamasi.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bilgiyon.pttemfilmuygulamasi.R
import com.bilgiyon.pttemfilmuygulamasi.common.TabPagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI();
    }

    fun setUpUI() {
        val tabLayout: TabLayout = findViewById(R.id.tablayout)
        val viewPager: ViewPager = findViewById(R.id.viewpager)
        viewPager.setAdapter(TabPagerAdapter(supportFragmentManager))
        tabLayout.setupWithViewPager(viewPager)
    }
}