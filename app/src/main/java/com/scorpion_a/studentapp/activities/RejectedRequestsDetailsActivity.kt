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
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_rejected_requests_details.*
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvCDateValue
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvCountValue
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvDEmailValue
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvIdValue
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvMoneyConfirmValue
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvNameInArabicValue
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvNameInEnglishValue
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvPriceValue
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvRequestDesc
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvRequestNumber
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvRequestStatusTitle
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvStudentStatusValue
import kotlinx.android.synthetic.main.activity_rejected_requests_details.tvTotalPriceValue

class RejectedRequestsDetailsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var receiptImage: ImageView
    lateinit var Rejected: ViewRequestsListData

    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rejected_requests_details)
        toolbar = header.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.rrd)
        val gson = Gson()

        Rejected= gson.fromJson(intent.extras?.getString("rejected"), ViewRequestsListData::class.java)

        tvRequestNumber.text=Rejected.id
        tvRequestDesc.text=Rejected.request_type.name.en
        tvCountValue.text=Rejected.count
        tvPriceValue.text=Rejected.price +" LE"
        tvTotalPriceValue.text=Rejected.total_price+" LE"
        tvCDateValue.text=Rejected.created_at.split("T")[0]
        tvRequestStatusTitle.text=Rejected.status

        tvNameInArabicValue.text=Rejected.student.name.ar
        tvNameInEnglishValue.text=Rejected.student.name.en
        tvIdValue.text=Rejected.student_id
        tvStudentStatusValue.text=Rejected.student.account_type
        tvDEmailValue.text=Rejected.student.email
        tvMoneyConfirmValue.text="Confirmed"



        val pagerAdapter =
            ImagesAdapter(this, Rejected.receipt)
        ivReceiptReqjected.setAdapter(pagerAdapter)
        ivReceiptReqjected.setPageMargin(20)

        // whenever the page changes

        // whenever the page changes

        if (Lang.getLang(this) == "ar") {
            ivReceiptReqjected.setRotationY(180f)
        }
//        receiptImage = findViewById(R.id.ivReceipt)
//        val image = ImageView(this)
//        image.setImageResource(R.drawable.receipt)
//
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