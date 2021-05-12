package com.what.tomorrow_school_lunch.UI.newMain.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.what.tomorrow_school_lunch.DataClass.UserSchoolInfo
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.Retrofit.SchoolInfoClient
import com.what.tomorrow_school_lunch.Retrofit.SchoolMeals.SchoolMealAPI
import com.what.tomorrow_school_lunch.Retrofit.SchoolMeals.SchoolMealInfo
import com.what.tomorrow_school_lunch.Retrofit.SchoolSelect.SchoolInfoAPI2
import com.what.tomorrow_school_lunch.UI.newMain.fragment.mealInfo.MealInfoFragment
import com.what.tomorrow_school_lunch.UI.newMain.fragment.mypage.MypageFragment
import com.what.tomorrow_school_lunch.UI.newMain.fragment.oneLineEvaluation.OnelineEvaluationFragment
import com.what.tomorrow_school_lunch.UI.newMain.fragment.ranking.RankingFragment

import com.what.tomorrow_school_lunch.databinding.ActivityBottomNavigationBinding
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class BottomNavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavigationBinding
    private lateinit var retService: SchoolMealAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_navigation)
        retService = SchoolInfoClient().getService().create(SchoolMealAPI::class.java)

        val currentDateTime = Calendar.getInstance().time
        var todayDate = SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(currentDateTime)
        binding.titleSchool.text = UserSchoolInfo.School_Name


        val schoolInfoSearchResponseLiveData1: LiveData<Response<SchoolMealInfo>> = liveData {
            val response = retService.searchSchoolMeal(
                UserSchoolInfo.Atpt_Ofcdc_Code,
                UserSchoolInfo.School_Code,
                todayDate,
                SchoolInfoAPI2.TYPE,
                SchoolInfoAPI2.PLNDEX,
                SchoolInfoAPI2.SIZE,
                SchoolInfoAPI2.KEY
            )
            emit(response)
        }
        schoolInfoSearchResponseLiveData1.observe(this, Observer {
            val aa = it.body()?.mealServiceDietInfo?.get(1)?.row?.get(0)?.DDISH_NM
            Toast.makeText(this@BottomNavigationActivity,"받아온 값 : $aa", Toast.LENGTH_SHORT).show()
        })



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