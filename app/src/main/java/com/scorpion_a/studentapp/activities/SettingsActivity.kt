package com.scorpion_a.studentapp.activities

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Connection
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.MyPreferences
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : BaseActivity() {
    lateinit var toolbar: Toolbar
    lateinit var mBtn : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.settings)
        Connection.isNetworkAvailable(this)

        tvSwitchMode.setOnClickListener {
//            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
//                Configuration.UI_MODE_NIGHT_YES ->
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                Configuration.UI_MODE_NIGHT_NO ->
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            }
            chooseThemeDialog()
        }

        mBtn = findViewById(R.id.tvLanguage)

        mBtn.setOnClickListener {

            showChangeLang()

        }

    }
    private fun showChangeLang() {

        val title = SpannableString(getString(R.string.choose_lang))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val arab = SpannableString("العربية")
        arab.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            arab.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val english = SpannableString("English")
        english.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            english.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val listItmes = arrayOf(arab, english)
        var checkedItem = -1
        if(Lang.getLang(this) == "ar"){
            checkedItem = 0
        }else if (Lang.getLang(this) == "en"){
            checkedItem = 1
        }

        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle(title)
        mBuilder.setSingleChoiceItems(listItmes, checkedItem) { dialog, which ->
            if (which == 0) {
                Lang.setLocate("ar", this)
                val intent = Intent(baseContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else if (which == 1) {
                Lang.setLocate("en", this)
                val intent = Intent(baseContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
        mDialog.window?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.alert)))
    }

    private fun chooseThemeDialog() {

        val title = SpannableString(getString(R.string.choose_mode))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val light = SpannableString(getString(R.string.light))
        light.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            light.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val dark = SpannableString(getString(R.string.dark))
        dark.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            dark.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val default = SpannableString(getString(R.string.system_default))
        default.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            default.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        val styles = arrayOf(light, dark, default)
        val checkedItem = MyPreferences(this).darkMode

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->

            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    MyPreferences(this).darkMode = 0
                    delegate.applyDayNight()
                    val intent = Intent(baseContext, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    MyPreferences(this).darkMode = 1
                    delegate.applyDayNight()
                    val intent = Intent(baseContext, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    MyPreferences(this).darkMode = 2
                    delegate.applyDayNight()
                    val intent = Intent(baseContext, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }

            }
        }

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.alert)))
    }

}