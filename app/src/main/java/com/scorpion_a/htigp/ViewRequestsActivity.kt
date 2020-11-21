package com.scorpion_a.htigp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.htigp.adapters.ViewRequestsListAdapter
import com.scorpion_a.htigp.model.ViewRequestsListData

class ViewRequestsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_requests)

        val viewRequestsListData: Array<ViewRequestsListData> = arrayOf<ViewRequestsListData>(
            ViewRequestsListData(
                "#188148 - Arabic Graduation certificate",
                "Status: Pending."
            ),
            ViewRequestsListData(
                "#188149 - English Graduation certificate",
                "Status: Accepted."
            ),
            ViewRequestsListData(
                "#188160 - Proof of Enrollment",
                "Status: Missing Requirements."
            )
        )

        val recyclerView: RecyclerView = findViewById(R.id.rvViewRequests)
        val adapter = ViewRequestsListAdapter(
            viewRequestsListData
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}