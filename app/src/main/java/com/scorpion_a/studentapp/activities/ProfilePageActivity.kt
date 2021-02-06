package com.scorpion_a.studentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
import kotlinx.android.synthetic.main.activity_profile_page.*

class ProfilePageActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="Profile Page"

        tvEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        buEditPass.setOnClickListener {
            val intent = Intent(this, EditPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}