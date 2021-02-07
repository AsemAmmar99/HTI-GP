package com.scorpion_a.studentapp.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
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
    }
}