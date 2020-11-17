package com.scorpion_a.htigp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val notificationListData: Array<NotificationListData> = arrayOf<NotificationListData>(
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 4:30 PM"
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 13 May"
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 13 May"
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 13 May"
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 18 Oct."
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 18 Oct."
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 18 Oct."
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 18 Oct."
            )
        )

        val recyclerView: RecyclerView = findViewById(R.id.rvNotification)
        val adapter = NotificationListAdapter(notificationListData)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}