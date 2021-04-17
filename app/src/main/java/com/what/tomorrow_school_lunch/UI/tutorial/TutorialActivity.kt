package com.what.tomorrow_school_lunch.UI.tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jem.liquidswipe.clippathprovider.LiquidSwipeClipPathProvider
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.tutorial.adapters.CustomPagerAdapter
import com.what.tomorrow_school_lunch.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    lateinit var binding : ActivityTutorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tutorial)

        val liquidSwipeClipPathProviders = Array(titleArray.count()) {
            LiquidSwipeClipPathProvider()
        }

        binding.viewpager.adapter = CustomPagerAdapter(this, liquidSwipeClipPathProviders)


        binding.viewpager.setOnTouchListener { _, event ->
            val waveCenterY = event.y
            liquidSwipeClipPathProviders.map {
                it.waveCenterY = waveCenterY
            }
            false
        }
    }
}