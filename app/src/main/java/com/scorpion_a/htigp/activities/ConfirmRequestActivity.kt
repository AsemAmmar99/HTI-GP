package com.scorpion_a.htigp.activities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.htigp.R
import kotlinx.android.synthetic.main.activity_confirm_request.*
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_request_data.*

class ConfirmRequestActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_request)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="Confirm Your Request"

        buConfirm.setOnClickListener {
            onRequestSent(this)
        }
    }

    private fun onRequestSent(context: Context) {
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.thank))
            .setMessage(getString(R.string.r_successfully_sent))
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