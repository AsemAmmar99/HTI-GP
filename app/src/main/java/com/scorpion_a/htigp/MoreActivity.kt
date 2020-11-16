package com.scorpion_a.htigp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)

        val moreListData: Array<MoreListData> = arrayOf<MoreListData>(
            MoreListData(
                "Profile Page"
            ),
            MoreListData(
                "Print Out"
            ),
            MoreListData(
                "Registration Proposal"
            ),
            MoreListData(
                "Test Your Self"
            ),
            MoreListData(
                "Tutorial Page"
            ),
            MoreListData(
                "FAQ and Support"
            ),
            MoreListData(
                "Application Technical Support"
            ),
            MoreListData(
                "Rate The App"
            ),
            MoreListData(
                "Log Out"
            ),
        )

        val recyclerView: RecyclerView = findViewById(R.id.rvMore)
        val adapter = MoreListAdapter(moreListData)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}