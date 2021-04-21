package com.scorpion_a.studentapp.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class Theme {
    companion object{

        public fun checkTheme(context: Context, delegate: AppCompatDelegate) {

            try {
                when (MyPreferences(context).darkMode) {
                    0 -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        delegate.applyDayNight()
                    }
                    1 -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        delegate.applyDayNight()
                    }
                    2 -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        delegate.applyDayNight()
                    }
                }
            }catch (ex: Exception){}
        }

    }
}