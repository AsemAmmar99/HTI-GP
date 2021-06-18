package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.TestsListAdapter
import com.scorpion_a.studentapp.model.TestsListData
import com.scorpion_a.studentapp.utils.Connection
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Theme
import kotlinx.android.synthetic.main.activity_profile_page.*

class TestYourselfActivity : BaseActivity() {
    lateinit var toolbar: Toolbar
    lateinit var  recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_your_self)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.test_yourself)
        Connection.isNetworkAvailable(this)

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