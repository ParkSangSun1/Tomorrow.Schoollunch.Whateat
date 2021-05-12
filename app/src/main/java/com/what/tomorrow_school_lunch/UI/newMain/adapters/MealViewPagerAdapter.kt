package com.what.tomorrow_school_lunch.UI.newMain.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.what.tomorrow_school_lunch.UI.newMain.fragment.mealInfo.time.DinnerFragment
import com.what.tomorrow_school_lunch.UI.newMain.fragment.mealInfo.time.LunchFragment
import com.what.tomorrow_school_lunch.UI.newMain.fragment.mealInfo.time.MorningFragment

class MealViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                MorningFragment()
            }
            1 -> {
                LunchFragment()
            }
            2 -> {
                DinnerFragment()
            }
            else -> {
                Fragment()
            }

        }
    }
}