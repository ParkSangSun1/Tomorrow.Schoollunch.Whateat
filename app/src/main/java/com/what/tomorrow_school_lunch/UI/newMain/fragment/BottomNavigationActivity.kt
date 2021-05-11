package com.what.tomorrow_school_lunch.UI.newMain.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.what.tomorrow_school_lunch.R

import com.what.tomorrow_school_lunch.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_navigation)
        replaceFragment(OnelineEvaluationFragment())
        binding.bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.one_line_evaluation -> {
                    replaceFragment(OnelineEvaluationFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.meal_info -> {
                    replaceFragment(MealInfoFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.ranking -> {
                    replaceFragment(RankingFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.mypage -> {
                    replaceFragment(MypageFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}