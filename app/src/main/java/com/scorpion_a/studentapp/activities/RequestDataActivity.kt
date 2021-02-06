package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scorpion_a.studentapp.R
import kotlinx.android.synthetic.main.activity_request_data.*

class RequestDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_data)
        tvEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
        buSendRequest.setOnClickListener {
            onRequestSent(this)
        }
    }

    private fun onRequestSent(context: Context) {
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.success))
            .setMessage(getString(R.string.successfully_sent))
            .setCancelable(false)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog: DialogInterface, which: Int ->
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
                dialog.dismiss()
            }
            .show()
    }
}