package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Connection
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_app_tech_support.*
import kotlinx.android.synthetic.main.activity_profile_page.header

class SupportActivity : BaseActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title=getString(R.string.htisupport)
        Connection.isNetworkAvailable(this)

        buSend.setOnClickListener {
            onSend(it.context)
        }
    }

    private fun onSend(context: Context) {

        val title = SpannableString(getString(R.string.thank))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.hti_message_sent))
        message.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            message.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(context)
        builder.setTitle(title)
            .setMessage(message)
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
            .window?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.alert)))
    }
}