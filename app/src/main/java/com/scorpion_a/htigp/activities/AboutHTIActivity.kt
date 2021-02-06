package com.scorpion_a.htigp.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.htigp.R
import kotlinx.android.synthetic.main.activity_about_hti.*
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header


class AboutHTIActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_hti)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="About HTI"

        tvemail.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "your_email"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject")
                intent.putExtra(Intent.EXTRA_TEXT, "your_text")
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {}
        }



    }
}