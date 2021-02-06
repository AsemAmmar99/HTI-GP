package com.scorpion_a.studentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scorpion_a.studentapp.R
import kotlinx.android.synthetic.main.activity_notification_details.*

class NotificationDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_details)

        buReply.setOnClickListener {
            val intent = Intent(this, NotificationReplyActivity::class.java)
            startActivity(intent)
        }
    }
}