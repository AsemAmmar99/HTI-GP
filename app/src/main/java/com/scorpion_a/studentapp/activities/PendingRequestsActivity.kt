package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.StaffRequestsListAdapter
import com.scorpion_a.studentapp.model.StaffRequestsListData
import kotlinx.android.synthetic.main.activity_pending_requests.*
import kotlinx.android.synthetic.main.activity_profile_page.header

class PendingRequestsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var staffRequestsListData: ArrayList<StaffRequestsListData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_requests)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.pending_requests)

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

        val adapterPending = StaffRequestsListAdapter(staffRequestsListData, this, "pending")
        rvStaffRequestP.setHasFixedSize(true)
        rvStaffRequestP.layoutManager = LinearLayoutManager(this)
        rvStaffRequestP.adapter = adapterPending

    }
}