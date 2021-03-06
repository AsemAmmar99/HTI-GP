package com.scorpion_a.htigp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.htigp.R
import com.scorpion_a.htigp.adapters.RegCardAdapter
import com.scorpion_a.htigp.adapters.TestsListAdapter
import com.scorpion_a.htigp.model.RequestListData
import com.scorpion_a.htigp.model.TestsListData
import kotlinx.android.synthetic.main.activity_profile_page.*

class TestYourselfActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var  recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_your_self)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="Test Yourself"

        val testsListData: Array<TestsListData> = arrayOf<TestsListData>(
            TestsListData(
                "Introduction to computer graphics"
            ),
            TestsListData(
                "Expert Systems"
            ),
            TestsListData(
                "Neural Networks"
            ),
            TestsListData(
                "English (1)"
            ),
            TestsListData(
                "English (2)"
            ),
            TestsListData(
                "Assembly"
            ),
            TestsListData(
                "Software (1)"
            ),
            TestsListData(
                "Software (2)"
            ),
            TestsListData(
                "Networks Programming"
            ),
            TestsListData(
                "Systems Design"
            ),
            TestsListData(
                "Simulation"
            )
        )

        recyclerView = findViewById(R.id.rvTests)
        var adapter = TestsListAdapter(testsListData, this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}