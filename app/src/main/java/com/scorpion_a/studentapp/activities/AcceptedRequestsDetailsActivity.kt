package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.ViewRequestsListData
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_profile_page.*

class AcceptedRequestsDetailsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var receiptImage: ImageView
    lateinit var Accepted: ViewRequestsListData
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accepted_requests_details)
        val gson = Gson()

        toolbar = header.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.ard)
        Accepted= gson.fromJson(intent.extras?.getString("Accepted"), ViewRequestsListData::class.java)

        tvRequestNumber.text=Accepted.id
        tvRequestDesc.text=Accepted.request_type.name.en
        tvCountValue.text=Accepted.count
        tvPriceValue.text=Accepted.price +" LE"
        tvTotalPriceValue.text=Accepted.total_price+" LE"
        tvCDateValue.text=Accepted.created_at.split("T")[0]
        tvRequestStatusTitle.text=Accepted.status

        tvNameInArabicValue.text=Accepted.student.name.ar
        tvNameInEnglishValue.text=Accepted.student.name.en
        tvIdValue.text=Accepted.student_id
        tvStudentStatusValue.text=Accepted.student.account_type
        tvDEmailValue.text=Accepted.student.email
        tvMoneyConfirmValue.text="Confirmed"
        receiptImage = findViewById(R.id.ivReceipt)
        val image = ImageView(this)
        image.setImageResource(R.drawable.receipt)

        receiptImage.setOnClickListener {
            if (image.getParent() != null) (image.getParent() as ViewGroup).removeView(
                image
            )
            var dialog = AlertDialog.Builder(this).setPositiveButton(getString(R.string.ok),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                        dialog?.dismiss()
                    }

                })

            dialog.setView(image).create().show()
        }

        buDone.setOnClickListener {
            onDone(it.context)
        }

    }

    private fun onDone(context: Context) {

        val title = SpannableString(getString(R.string.thank_you))
        title.setSpan(
            ForegroundColorSpan(getResources().getColor(R.color.light_black)),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val message = SpannableString(getString(R.string.srhbmad))
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