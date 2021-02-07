package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.PropCardAdapter
import com.scorpion_a.studentapp.adapters.ResultsCardAdapter
import com.scorpion_a.studentapp.model.PropCardData
import com.scorpion_a.studentapp.model.ResultsCardData
import kotlinx.android.synthetic.main.activity_profile_page.*

class ResultsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="My Results"

        val ResultsCardItems: Array<ResultsCardData> = arrayOf<ResultsCardData>(
            ResultsCardData(
                "1",
                "BSC110",
                "DSS",
                "A"
            ),
            ResultsCardData(
                "2",
                "BSC110",
                "AI",
                "A"
            ),
            ResultsCardData(
                "3",
                "BSC110",
                "Algorithms",
                "A"
            ),
            ResultsCardData(
                "4",
                "BSC110",
                "System Design",
                "A"
            ),
            ResultsCardData(
                "5",
                "BSC110",
                "Neural",
                "B"
            ),
            ResultsCardData(
                "6",
                "BSC110",
                "PHE",
                "B+"
            )
        )

        recyclerView = findViewById(R.id.rvResultsItems)
        var adapter = ResultsCardAdapter(ResultsCardItems)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}