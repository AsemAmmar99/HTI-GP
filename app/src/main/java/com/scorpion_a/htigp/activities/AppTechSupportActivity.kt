package com.scorpion_a.htigp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.htigp.R
import kotlinx.android.synthetic.main.activity_app_tech_support.*
import kotlinx.android.synthetic.main.activity_profile_page.header

class AppTechSupportActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_tech_support)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="Application Technical Support"

        buSend.setOnClickListener {
            finish()
        }
    }
}