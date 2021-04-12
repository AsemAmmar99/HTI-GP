package com.scorpion_a.studentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.MyPreferences
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.tvSwitchMode
import kotlinx.android.synthetic.main.activity_staff_settings.*

class StaffSettingsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var mBtn : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_settings)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.settings)

        tvSwitchSMode.setOnClickListener {
//            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
//                Configuration.UI_MODE_NIGHT_YES ->
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                Configuration.UI_MODE_NIGHT_NO ->
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            }
            chooseThemeDialog()
        }

        mBtn = findViewById(R.id.tvSLanguage)

        mBtn.setOnClickListener {

            showChangeLang()

        }

    }
    private fun showChangeLang() {

        val listItmes = arrayOf("العربية", "English")

        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle(getString(R.string.choose_lang))
        mBuilder.setSingleChoiceItems(listItmes, -1) { dialog, which ->
            if (which == 0) {
                Lang.setLocate("ar", this)
                val intent = Intent(baseContext, StaffHomeActivity::class.java)
                startActivity(intent)
                finish()
            } else if (which == 1) {
                Lang.setLocate("en", this)
                val intent = Intent(baseContext, StaffHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()

        mDialog.show()

    }

    private fun chooseThemeDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choose_mode))
        val styles = arrayOf(getString(R.string.light), getString(R.string.dark), getString(R.string.system_default))
        val checkedItem = MyPreferences(this).darkMode

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->

            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    MyPreferences(this).darkMode = 0
                    delegate.applyDayNight()
                    val intent = Intent(baseContext, StaffHomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    MyPreferences(this).darkMode = 1
                    delegate.applyDayNight()
                    val intent = Intent(baseContext, StaffHomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    MyPreferences(this).darkMode = 2
                    delegate.applyDayNight()
                    val intent = Intent(baseContext, StaffHomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }

            }
        }

        val dialog = builder.create()
        dialog.show()
    }

}