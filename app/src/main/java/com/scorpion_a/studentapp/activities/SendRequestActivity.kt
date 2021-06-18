package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Connection
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_send_request.*

class SendRequestActivity : BaseActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_request)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="Sending Request .."
        Connection.isNetworkAvailable(this)

        buNo.setOnClickListener {
            onNotPaying(it.context)
        }
        buYes.setOnClickListener {
            startActivity( Intent (this, RequestDataActivity::class.java))
        }
    }

    private fun onNotPaying(context: Context) {
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.necessary_request))
            .setMessage(getString(R.string.you_must_pay_first))
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