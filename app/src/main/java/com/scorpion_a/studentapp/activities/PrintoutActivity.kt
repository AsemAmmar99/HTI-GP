package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.RegCardAdapter
import com.scorpion_a.studentapp.model.RegCardData
import kotlinx.android.synthetic.main.activity_profile_page.*

class PrintoutActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_printout)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="Printout"

//        val regCardTitles: Array<RegCardData> = arrayOf<RegCardData>(
//            RegCardData(
//                "N",
//                "Code",
//                "Subject",
//                "Group",
//                "Units"
//            )
//        )

        val regCardItems: Array<RegCardData> = arrayOf<RegCardData>(
            RegCardData(
                "1",
                "BSC110",
                "DSS",
                "1",
                "1"
            ),
            RegCardData(
                "2",
                "BSC110",
                "AI",
                "1",
                "1"
            ),
            RegCardData(
                "3",
                "BSC110",
                "Algorithms",
                "1",
                "1"
            ),
            RegCardData(
                "4",
                "BSC110",
                "System Design",
                "1",
                "1"
            ),
            RegCardData(
                "5",
                "BSC110",
                "Neural",
                "1",
                "1"
            ),
            RegCardData(
                "6",
                "BSC110",
                "PHE",
                "1",
                "1"
            )
        )


        recyclerView = findViewById(R.id.rvRegCardItems)
        var adapter = RegCardAdapter(regCardItems)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}