package com.scorpion_a.htigp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        val requestListData: Array<RequestListData> = arrayOf<RequestListData>(
            RequestListData(
                "Arabic Graduation certificate"
            ),
            RequestListData(
                "English Graduation certificate"
            ),
            RequestListData(
                "Arab equivalent certificate"
            ),
            RequestListData(
                "Certificate of good conduct"
            ),
            RequestListData(
                "Arabic estimate statement"
            ),
            RequestListData(
                "Arabic Graduation certificate"
            ),
            RequestListData(
                "English statement of estimates"
            ),
            RequestListData(
                "Proof of enrollment"
            ),
            RequestListData(
                "English Certificate of study materials"
            ),
        )

        val recyclerView: RecyclerView = findViewById(R.id.rvRequest)
        val adapter = RequestListAdapter(requestListData)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}