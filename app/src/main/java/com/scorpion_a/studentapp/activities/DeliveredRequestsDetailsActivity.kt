package com.scorpion_a.studentapp.activities

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.ImagesAdapter
import com.scorpion_a.studentapp.model.ViewRequestsListData
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_accepted_requests_details.*
import kotlinx.android.synthetic.main.activity_delivered_requests_details.*
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvCDateValue
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvCountValue
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvDEmailValue
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvIdValue
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvMoneyConfirmValue
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvNameInArabicValue
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvNameInEnglishValue
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvPriceValue
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvRequestDesc
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvRequestNumber
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvRequestStatusTitle
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvStudentStatusValue
import kotlinx.android.synthetic.main.activity_delivered_requests_details.tvTotalPriceValue
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header

class DeliveredRequestsDetailsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var receiptImage: ImageView
    lateinit var delivered: ViewRequestsListData

    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivered_requests_details)
        val gson = Gson()

        toolbar = header.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.drd)
        delivered= gson.fromJson(intent.extras?.getString("delivered"), ViewRequestsListData::class.java)
        tvRequestNumber.text=delivered.id
        tvRequestDesc.text=delivered.request_type.name.en
        tvCountValue.text=delivered.count
        tvPriceValue.text=delivered.price +" LE"
        tvTotalPriceValue.text=delivered.total_price+" LE"
        tvCDateValue.text=delivered.created_at.split("T")[0]
        tvRequestStatusTitle.text=delivered.status

        tvNameInArabicValue.text=delivered.student.name.ar
        tvNameInEnglishValue.text=delivered.student.name.en
        tvIdValue.text=delivered.student_id
        tvStudentStatusValue.text=delivered.student.account_type
        tvDEmailValue.text=delivered.student.email
        tvMoneyConfirmValue.text="Confirmed"

        val pagerAdapter =
            ImagesAdapter(this, delivered.receipt)
        ivReceiptDeliverd.setAdapter(pagerAdapter)
        ivReceiptDeliverd.setPageMargin(20)





//        receiptImage.setOnClickListener {
//            if (image.getParent() != null) (image.getParent() as ViewGroup).removeView(
//                image
//            )
//            var dialog = AlertDialog.Builder(this).setPositiveButton(getString(R.string.ok),
//                object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int) {
//
//                        dialog?.dismiss()
//                    }
//
//                })
//
//            dialog.setView(image).create().show()
//        }
    }
}