package com.scorpion_a.htigp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buLogout.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finishAffinity()
        }

        val newsListData: Array<NewsListData> = arrayOf<NewsListData>(
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            ),
            NewsListData(
                "The Institute Guide to Coordination",
                "DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy"
            )
        )

        val recyclerView: RecyclerView = findViewById(R.id.rvNews)
        val adapter = NewsListAdapter(newsListData)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}