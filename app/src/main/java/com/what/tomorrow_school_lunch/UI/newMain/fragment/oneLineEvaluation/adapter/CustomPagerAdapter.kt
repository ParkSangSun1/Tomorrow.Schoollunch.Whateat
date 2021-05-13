package com.what.tomorrow_school_lunch.UI.newMain.fragment.oneLineEvaluation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.newMain.fragment.oneLineEvaluation.MealEvaluationFragment

class CustomPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val PAGENUMBER = 3

    override fun getCount(): Int {
        return PAGENUMBER
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MealEvaluationFragment.newInstance(R.raw.smile1, "아침","오늘 급식은 먹을만 해요")
            1 -> MealEvaluationFragment.newInstance(R.raw.smile2, "점심","와우 오늘은 포식")
            else -> MealEvaluationFragment.newInstance(R.raw.angry, "저녁","오늘은 힘든날이네요")
        }
    }
}