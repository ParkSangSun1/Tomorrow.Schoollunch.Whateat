package com.what.tomorrow_school_lunch.UI.newMain.mealInfo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.what.tomorrow_school_lunch.UI.newMain.fragment.MealInfoFragment
import com.what.tomorrow_school_lunch.UI.newMain.fragment.MypageFragment
import com.what.tomorrow_school_lunch.UI.newMain.fragment.OnelineEvaluationFragment
import com.what.tomorrow_school_lunch.UI.newMain.fragment.RankingFragment

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