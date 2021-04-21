package com.scorpion_a.studentapp.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.StaffRequestsListAdapter
import com.scorpion_a.studentapp.model.StaffRequestsListData
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_delivered_requests.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.staff_request_item.*


class DeliveredRequestsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var staffRequestsListData: ArrayList<StaffRequestsListData>
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivered_requests)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.delivered_requests)

        staffRequestsListData = ArrayList<StaffRequestsListData>()
        staffRequestsListData.add(StaffRequestsListData(
            "123"
        ))
        staffRequestsListData.add(StaffRequestsListData(
            "123"
        ))
        staffRequestsListData.add(StaffRequestsListData(
            "245"
        ))
        staffRequestsListData.add(StaffRequestsListData(
            "3567"
        ))
        staffRequestsListData.add(StaffRequestsListData(
            "44444"
        ))
        staffRequestsListData.add(StaffRequestsListData(
            "6666"
        ))
        staffRequestsListData.add(StaffRequestsListData(
            "333333"
        ))
        staffRequestsListData.add(StaffRequestsListData(
            "37252"
        ))
        staffRequestsListData.add(StaffRequestsListData(
            "12734"
        ))
        staffRequestsListData.add(StaffRequestsListData(
            "421098"
        ))
        staffRequestsListData.add(StaffRequestsListData(
            "245213"
        ))

        val adapterDelivered = StaffRequestsListAdapter(staffRequestsListData, this, "delivered")
        rvStaffRequestD.setHasFixedSize(true)
        rvStaffRequestD.layoutManager = LinearLayoutManager(this)
        rvStaffRequestD.adapter = adapterDelivered

    }
}