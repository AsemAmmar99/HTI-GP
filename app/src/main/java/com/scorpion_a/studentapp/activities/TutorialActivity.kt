package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.TutorialsListAdapter
import com.scorpion_a.studentapp.model.TutorialsListData
import kotlinx.android.synthetic.main.activity_profile_page.*

class TutorialActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var  recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="Tutorials Page"

        val tutorialsListData: Array<TutorialsListData> = arrayOf<TutorialsListData>(
            TutorialsListData(
                "Login"
            ),
            TutorialsListData(
                "Home"
            ),
            TutorialsListData(
                "Requests"
            ),
            TutorialsListData(
                "Profile"
            ),
            TutorialsListData(
                "Notifications"
            ),
            TutorialsListData(
                "FAQs and Support"
            ),
            TutorialsListData(
                "Application Technical Support"
            ),
            TutorialsListData(
                "Tutorial"
            ),
            TutorialsListData(
                "Test Yourself"
            ),
            TutorialsListData(
                "Printout"
            ),
            TutorialsListData(
                "Registration Proposal"
            ),
        )

        recyclerView = findViewById(R.id.rvTutorials)
        var adapter = TutorialsListAdapter(tutorialsListData, this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}