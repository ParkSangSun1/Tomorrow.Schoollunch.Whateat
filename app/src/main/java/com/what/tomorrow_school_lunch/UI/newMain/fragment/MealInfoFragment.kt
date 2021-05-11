package com.what.tomorrow_school_lunch.UI.newMain.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.main.adapters.ViewPagerAdapter
import com.what.tomorrow_school_lunch.UI.newMain.mealInfo.MealViewPagerAdapter
import com.what.tomorrow_school_lunch.databinding.FragmentMealInfoBinding
import com.what.tomorrow_school_lunch.databinding.FragmentTodayMealBinding


class MealInfoFragment : Fragment() {

    lateinit var binding: FragmentMealInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meal_info, container, false)


        val adapter = fragmentManager?.let { MealViewPagerAdapter(it, lifecycle) }
        binding.mealViewPager2.adapter = adapter

        TabLayoutMediator(binding.mealTabLayout, binding.mealViewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "아침"
                }
                1 -> {
                    tab.text = "점심"
                }
                2 -> {
                    tab.text = "저녁"
                }
            }
        }.attach()

        return binding.root
    }


}