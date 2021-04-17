package com.what.tomorrow_school_lunch.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.tutorial.TutorialActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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

    }
}