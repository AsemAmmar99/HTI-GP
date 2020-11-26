package com.scorpion_a.htigp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.htigp.R
import com.scorpion_a.htigp.adapters.PropCardAdapter
import com.scorpion_a.htigp.model.PropCardData
import kotlinx.android.synthetic.main.activity_profile_page.*

class RegProposalActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_proposal)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title="Registration Proposal"

        val propCardItems: Array<PropCardData> = arrayOf<PropCardData>(
            PropCardData(
                "1",
                "BSC110",
                "DSS",
                "1"
            ),
            PropCardData(
                "2",
                "BSC110",
                "AI",
                "1"
            ),
            PropCardData(
                "3",
                "BSC110",
                "Algorithms",
                "1"
            ),
            PropCardData(
                "4",
                "BSC110",
                "System Design",
                "1"
            ),
            PropCardData(
                "5",
                "BSC110",
                "Neural",
                "1"
            ),
            PropCardData(
                "6",
                "BSC110",
                "PHE",
                "1"
            )
        )

        recyclerView = findViewById(R.id.rvPropCardItems)
        var adapter = PropCardAdapter(propCardItems)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}