package com.scorpion_a.studentapp.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import java.util.*

public class Lang {
    companion object{


      fun setLocate(Lang: String, context: Context){

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)

        val editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    public fun loadLocate(context: Context) {
        val sharedPreferences = context.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null) {
            setLocate(language, context)
        }
    }
    }
}