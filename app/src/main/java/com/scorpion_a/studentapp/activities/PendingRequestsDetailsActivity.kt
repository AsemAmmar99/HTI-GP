package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_pending_requests_details.*
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header


class PendingRequestsDetailsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var receiptImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_requests_details)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title=getString(R.string.prd)

        receiptImage = findViewById(R.id.ivReceipt)
        val image = ImageView(this)
        image.setImageResource(R.drawable.receipt)

        receiptImage.setOnClickListener{
            if (image.getParent() != null) (image.getParent() as ViewGroup).removeView(
                image
            )
           var dialog= AlertDialog.Builder(this).setPositiveButton(getString(R.string.ok),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                        dialog?.dismiss()
                    }

                })

            dialog.setView(image).create().show()
            dialog.setView(image).create().window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        }

        buAccept.setOnClickListener {
            onAccept(it.context)
        }

        buReject.setOnClickListener {
            val intent = Intent(it.context, RejectReasonActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onAccept(context: Context) {

        val title = SpannableString(getString(R.string.pa))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.asr))
        message.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            message.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val builder: androidx.appcompat.app.AlertDialog.Builder
        builder = androidx.appcompat.app.AlertDialog.Builder(context)
        builder.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                getString(R.string.yes)
            ) { dialog: DialogInterface, which: Int ->
                onAcceptYes(context)
                dialog.dismiss()
            }.setNegativeButton(
                getString(R.string.cancel)
            ) { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
            }
            .show()
            .window?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.alert)))
    }

    private fun onAcceptYes(context: Context) {

        val title = SpannableString(getString(R.string.thank_you))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.srhba))
        message.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            message.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val builder: androidx.appcompat.app.AlertDialog.Builder
        builder = androidx.appcompat.app.AlertDialog.Builder(context)
        builder.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                getString(R.string.ok)
            ) { dialog: DialogInterface, which: Int ->
                val intent = Intent(context, StaffHomeActivity::class.java)
                startActivity(intent)
                finish()
                dialog.dismiss()
            }
            .show()
            .window?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.alert)))
    }


}