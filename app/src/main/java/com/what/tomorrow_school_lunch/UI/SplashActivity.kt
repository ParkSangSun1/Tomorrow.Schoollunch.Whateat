package com.what.tomorrow_school_lunch.UI

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.what.tomorrow_school_lunch.UI.main.MainActivity
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.tutorial.TutorialActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var mPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mPreferences = getSharedPreferences("FIRST_START", MODE_PRIVATE)

        val checkFirst = mPreferences.getBoolean("FIRST_START",false)
        if(checkFirst==false){
            val editor: SharedPreferences.Editor = mPreferences.edit()
            editor.putBoolean("FIRST_START",true)
            editor.commit()

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
        }else{
            Handler().postDelayed(
                {

                    startActivity(
                        Intent(
                            this@SplashActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()
                },
                2000
            )
        }



    }
}