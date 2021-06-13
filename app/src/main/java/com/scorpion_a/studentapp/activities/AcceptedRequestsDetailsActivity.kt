package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.model.ViewRequestsListData
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_accepted_requests_details.*
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header


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
    }
}