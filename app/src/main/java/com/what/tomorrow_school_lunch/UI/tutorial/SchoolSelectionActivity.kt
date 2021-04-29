package com.what.tomorrow_school_lunch.UI.tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.what.tomorrow_school_lunch.DataClass.Contants
import com.what.tomorrow_school_lunch.DataClass.RESPONSE_STATE
import com.what.tomorrow_school_lunch.DataClass.SchoolInfoAPI
import com.what.tomorrow_school_lunch.DataClass.retrofit.SchoolInfoRetrofitManager
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.databinding.ActivitySchoolSelectionBinding

class SchoolSelectionActivity : AppCompatActivity() {
    lateinit var binding :ActivitySchoolSelectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_school_selection)

        binding.findSchoolBtn.setOnClickListener {

            SchoolInfoAPI.SCHUL_NM = binding.schoolName.text.toString()
            Log.d(Contants.TAG,"학교 이름 액티비티 : ${binding.schoolName.text}")
            Log.d(Contants.TAG,"키 : ${SchoolInfoAPI.KEY}")

            //검색 api 호출
            SchoolInfoRetrofitManager.instance.searchSchool(searchTerm = "&SCHUL_NM=${binding.schoolName.text}", completion ={
                    responseState, responseBody ->
                when(responseState){
                    RESPONSE_STATE.OKAY->{
                        Log.i("로그","api 호출성공 : $responseBody")
                    }
                    RESPONSE_STATE.FAIL->{
                        Toast.makeText(this,"api호출 에러", Toast.LENGTH_SHORT).show()
                        Log.i("로그","api 호출실패 : $responseBody")
                    }
                }
            } )
        }
    }

}