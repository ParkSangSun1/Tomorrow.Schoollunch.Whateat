package com.what.tomorrow_school_lunch.UI.tutorial.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.what.tomorrow_school_lunch.UI.tutorial.TutorialFragment
import com.what.tomorrow_school_lunch.UI.tutorial.backgroundColorArray
import com.what.tomorrow_school_lunch.UI.tutorial.resourceArray
import com.what.tomorrow_school_lunch.UI.tutorial.titleArray

class CustomFragmentPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return TutorialFragment.newInstance(
            backgroundColorArray[(position % titleArray.count())],
            resourceArray[(position % titleArray.count())],
            titleArray[(position % titleArray.count())]
        )
    }

    override fun getCount(): Int {
        return titleArray.count() * 20
    }
}