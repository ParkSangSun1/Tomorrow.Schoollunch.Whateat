package com.what.tomorrow_school_lunch.UI.SchoolPerformMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.what.tomorrow_school_lunch.DataClass.UserSchoolInfo
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.Retrofit.SchoolInfoClient
import com.what.tomorrow_school_lunch.Retrofit.SchoolMeals.SchoolMealAPI
import com.what.tomorrow_school_lunch.Retrofit.SchoolMeals.SchoolMealInfo
import com.what.tomorrow_school_lunch.Retrofit.SchoolSelect.SchoolInfoAPI2
import com.what.tomorrow_school_lunch.databinding.ActivitySchoolPerformMainBinding
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class SchoolPerformMainActivity : AppCompatActivity() {
    lateinit var binding: ActivitySchoolPerformMainBinding
    private lateinit var retService: SchoolMealAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_school_perform_main)
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

        schoolInfoSearchResponseLiveData1.observe(this, androidx.lifecycle.Observer {
            val aa = it.body()?.mealServiceDietInfo?.get(1)?.row?.get(0)?.DDISH_NM
            Toast.makeText(this, "받아온 값 : $aa", Toast.LENGTH_SHORT).show()

            var mealListM =
                it.body()?.mealServiceDietInfo?.get(1)?.row?.get(0)?.DDISH_NM?.split("<br/>")
            var mealListL =
                it.body()?.mealServiceDietInfo?.get(1)?.row?.get(1)?.DDISH_NM?.split("<br/>")
            var mealListN =
                it.body()?.mealServiceDietInfo?.get(1)?.row?.get(2)?.DDISH_NM?.split("<br/>")


            binding.mealM.text = mealListM?.let { it1 -> getMealInfo(it1) }
            binding.mealL.text = mealListL?.let { it1 -> getMealInfo(it1) }
            binding.mealN.text = mealListN?.let { it1 -> getMealInfo(it1) }

        })


    }


    fun getMealInfo(mealList: List<String>): String {
        var DDISH_NM = ""

        for (meal in mealList) {
            var st = meal.substringBefore(".")
            st = st.substringBefore("*")
            st = st.substringBefore("/")

            for (i in 0..9) {
                st = st.substringBefore(i.toString())
            }
            DDISH_NM += st + "\n"
        }

        return DDISH_NM
    }
}