package com.what.tomorrow_school_lunch.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.what.tomorrow_school_lunch.DataClass.UserSchoolInfo
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.newMain.fragment.BottomNavigationActivity
import com.what.tomorrow_school_lunch.UI.tutorial.TutorialActivity
import com.what.tomorrow_school_lunch.Util.PreferenceUtil

class SplashActivity : AppCompatActivity() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        prefs.getString("FIRST_START","")
//        prefs = ("FIRST_START", MODE_PRIVATE)

        //true 실행 해봄, false 실행 안해봄
        val checkFirst :Boolean= prefs.getString("FIRST_START", "false").toString().toBoolean()
        if (checkFirst == false) {
            prefs.setString("FIRST_START","true")

            Handler().postDelayed(
                {

                    startActivity(
                        Intent(
                            this@SplashActivity,
                            TutorialActivity::class.java
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

            Handler().postDelayed(
                {

                    startActivity(
                        Intent(
                            this@SplashActivity,
                            BottomNavigationActivity::class.java
                        )
                    )
                    finish()
                },
                2000
            )
        }


    }
}