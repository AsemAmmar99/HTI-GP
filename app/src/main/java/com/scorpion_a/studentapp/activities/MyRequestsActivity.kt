package com.scorpion_a.studentapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.ViewRequestsListAdapter
import com.scorpion_a.studentapp.model.ViewRequestsListData
import kotlinx.android.synthetic.main.activity_my_requests_page.*
import kotlinx.android.synthetic.main.fragment_notification.view.*

class MyRequestsActivity : AppCompatActivity() {
    lateinit var  recyclerView: RecyclerView
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_requests_page)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="My Requests Page"
        val viewRequestsListData: Array<ViewRequestsListData> = arrayOf<ViewRequestsListData>(
            ViewRequestsListData(
                "188148",
                "Arabic Graduation certificate",
                "Pending",
                "2",
                "4:00PM 4/4/2021"
            ),
            ViewRequestsListData(
                "188149",
                "English Graduation certificate zzzzzzzzzzzzzzzzzzz",
                "Done",
                "2",
                "4:00PM 4/4/2021"
            ),
            ViewRequestsListData(
                "188160",
                "Proof of Enrollment",
                "Missing Requirements",
                "2",
                "4:00PM 4/4/2021"
            )
        )

        recyclerView= findViewById(R.id.rvViewRequests)
        val adapter = ViewRequestsListAdapter(
            viewRequestsListData
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
