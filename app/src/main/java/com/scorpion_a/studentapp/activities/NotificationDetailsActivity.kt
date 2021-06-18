package com.scorpion_a.studentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Connection
import kotlinx.android.synthetic.main.activity_notification_details.*

class NotificationDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_details)
        Connection.isNetworkAvailable(this)

        buReply.setOnClickListener {
            val intent = Intent(this, NotificationReplyActivity::class.java)
            startActivity(intent)
        }
    }
}