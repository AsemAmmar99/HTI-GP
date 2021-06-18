package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Connection
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Theme

class FAQsAnswersActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs_answers)
        Connection.isNetworkAvailable(this)

    }
}