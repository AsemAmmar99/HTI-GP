package com.scorpion_a.studentapp.activities

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.scorpion_a.studentapp.R
import kotlinx.android.synthetic.main.activity_about_hti.*
import kotlinx.android.synthetic.main.activity_profile_page.header


class AboutHTIActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    val MY_PERMISSIONS_REQUEST_CALL_PHONE=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_hti)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.about_hti)

        tvPhoneValue.setOnClickListener {

            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:+20554351292")
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this as Activity,
                        Manifest.permission.CALL_PHONE)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        MY_PERMISSIONS_REQUEST_CALL_PHONE)
                }
            }
            startActivity(callIntent)
        }

        tvemail.setOnClickListener {
            try {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "support@hti.edu.eg"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Question")

                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                //TODO smth
            }
        }
        tvwebsite.setOnClickListener {
            val uriUrl = Uri.parse("http://hti.edu.eg/")
            val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
            startActivity(launchBrowser)
        }
        tvfacebook.setOnClickListener {
            val uriUrl = Uri.parse("http://www.facebook.com/HTI.EGY/")
            val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
            startActivity(launchBrowser)
        }


        tvinstagram.setOnClickListener {
            val uriUrl = Uri.parse("https://www.instagram.com/htiofficial/")
            val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
            startActivity(launchBrowser)
        }

        tvyoutube.setOnClickListener {
            val uriUrl = Uri.parse("https://www.youtube.com/channel/UCDbk3rzKJcrFLufxUv-gLHA")
            val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
            startActivity(launchBrowser)

        }
        tvtwitter.setOnClickListener {
            val uriUrl = Uri.parse("https://twitter.com/hti_official/")
            val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
            startActivity(launchBrowser)
        }
    }
}