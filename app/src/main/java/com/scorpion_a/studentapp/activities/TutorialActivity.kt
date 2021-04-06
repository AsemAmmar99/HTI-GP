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
        toolbar.title= getString(R.string.tutorials)

        val tutorialsListData: Array<TutorialsListData> = arrayOf<TutorialsListData>(
            TutorialsListData(
                "Login Screen"
            ),
            TutorialsListData(
                "Home Screen"
            ),
            TutorialsListData(
                "Events and News"
            ),
            TutorialsListData(
                "Requests Screen"
            ),
            TutorialsListData(
                "Profile Page"
            ),
            TutorialsListData(
                "Notifications Screen"
            ),
            TutorialsListData(
                "FAQs and Support Screen"
            ),
            TutorialsListData(
                "My Requests Screen"
            ),
            TutorialsListData(
                "Test Yourself Screen"
            ),
        )

        recyclerView = findViewById(R.id.rvTutorials)
        var adapter = TutorialsListAdapter(tutorialsListData, this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}