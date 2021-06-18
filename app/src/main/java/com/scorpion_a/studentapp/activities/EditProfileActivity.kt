package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Connection
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_profile_page.header

class EditProfileActivity : BaseActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="Editing Profile"

        Connection.isNetworkAvailable(this)

        buSave.setOnClickListener {
            finish()
        }
    }
}