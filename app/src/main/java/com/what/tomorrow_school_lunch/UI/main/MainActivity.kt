package com.what.tomorrow_school_lunch.UI.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.google.android.material.tabs.TabLayoutMediator
import com.what.tomorrow_school_lunch.DataClass.UserSchoolInfo
import com.what.tomorrow_school_lunch.DataClass.UserSchoolInfo.School_Name
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.Retrofit.SchoolInfoClient
import com.what.tomorrow_school_lunch.Retrofit.SchoolMeals.SchoolMealAPI
import com.what.tomorrow_school_lunch.Retrofit.SchoolMeals.SchoolMealInfo
import com.what.tomorrow_school_lunch.Retrofit.SchoolSelect.SchoolInfo
import com.what.tomorrow_school_lunch.Retrofit.SchoolSelect.SchoolInfoAPI
import com.what.tomorrow_school_lunch.Retrofit.SchoolSelect.SchoolInfoAPI2
import com.what.tomorrow_school_lunch.UI.SplashActivity
import com.what.tomorrow_school_lunch.UI.main.adapters.ViewPagerAdapter
import com.what.tomorrow_school_lunch.databinding.ActivityMainBinding
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var retService: SchoolMealAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        retService = SchoolInfoClient().getService().create(SchoolMealAPI::class.java)

        val currentDateTime = Calendar.getInstance().time
        var todayDate = SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(currentDateTime)
        binding.titleSchool.text = School_Name


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
            Toast.makeText(this@MainActivity,"받아온 값 : $aa",Toast.LENGTH_SHORT).show()
        })


        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "한줄평"
                }
                1 -> {
                    tab.text = "급식"
                }
            }
        }.attach()
    }
}