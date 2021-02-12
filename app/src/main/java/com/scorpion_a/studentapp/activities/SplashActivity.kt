package com.scorpion_a.studentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.SharedPreferenceClass

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hiding title bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        //4second splash time
        Handler().postDelayed({
            //start main activity
            if (SharedPreferenceClass.loadString(this,"TOKEN").isEmpty()) {
                startActivity(Intent(this@SplashActivity, LoginScreen::class.java))
                finish()
            }else{
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            }
            //finish this activity
        },2000)

    }
}