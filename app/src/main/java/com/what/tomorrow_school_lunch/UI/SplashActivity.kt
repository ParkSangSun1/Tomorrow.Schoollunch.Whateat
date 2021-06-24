package com.what.tomorrow_school_lunch.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.what.tomorrow_school_lunch.DataClass.UserSchoolInfo
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.SchoolPerformMain.SchoolPerformMainActivity
import com.what.tomorrow_school_lunch.UI.newMain.fragment.BottomNavigationActivity
import com.what.tomorrow_school_lunch.UI.tutorial.SchoolSelectionActivity
import com.what.tomorrow_school_lunch.UI.tutorial.TutorialActivity
import com.what.tomorrow_school_lunch.Util.PreferenceUtil

class SplashActivity : AppCompatActivity() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        prefs.getString("FIRST_START","")

        //true 실행 해봄, false 실행 안해봄
        val checkFirst :Boolean= prefs.getString("FIRST_START", "false").toString().toBoolean()
        Log.d("로그",checkFirst.toString())
        if (checkFirst == false) {
            prefs.setString("FIRST_START","true")

            Handler().postDelayed(
                {

                    startActivity(
                        Intent(
                            this@SplashActivity,
                            SchoolSelectionActivity::class.java
                        )
                    )
                    finish()
                },
                2000
            )
        } else {
            UserSchoolInfo.School_Name = prefs.getString("SCHUL_NM","")
            UserSchoolInfo.School_Code = prefs.getString("SD_SCHUL_CODE","")
            UserSchoolInfo.Atpt_Ofcdc_Code = prefs.getString("ATPT_OFCDC_SC_CODE","")
//            db.collection("schoolname").document(UserSchoolInfo.School_Name)
//                .get()

            Handler().postDelayed(
                {

                    startActivity(
                        Intent(
                            this@SplashActivity,
                            SchoolPerformMainActivity::class.java
                        )
                    )
                    finish()
                },
                2000
            )
        }


    }
}