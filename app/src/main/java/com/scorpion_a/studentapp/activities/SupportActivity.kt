package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
import kotlinx.android.synthetic.main.activity_app_tech_support.*
import kotlinx.android.synthetic.main.activity_profile_page.header

class SupportActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title=getString(R.string.htisupport)

        buSend.setOnClickListener {
            onSend(it.context)
        }
    }

    private fun onSend(context: Context) {
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.thank))
            .setMessage(getString(R.string.hti_message_sent))
            .setCancelable(false)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("menu", 1)
                startActivity(intent)
            }
            .show()
    }
}