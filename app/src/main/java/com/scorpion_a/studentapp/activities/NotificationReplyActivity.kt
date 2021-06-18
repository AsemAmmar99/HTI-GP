package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Connection
import kotlinx.android.synthetic.main.activity_notification_reply.*

class NotificationReplyActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_reply)
        Connection.isNetworkAvailable(this)

        buSend.setOnClickListener {
            finish()
        }
    }
}