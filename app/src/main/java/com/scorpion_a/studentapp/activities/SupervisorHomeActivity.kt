package com.scorpion_a.studentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.PropCardAdapter
import com.scorpion_a.studentapp.adapters.SupervisorCardAdapter
import com.scorpion_a.studentapp.model.PropCardData
import com.scorpion_a.studentapp.model.SupervisorCardData
import kotlinx.android.synthetic.main.activity_supervisor_home.*

class SupervisorHomeActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervisor_home)

        tvLogout.setOnClickListener {
            val intent = Intent(it.context, LoginScreen::class.java)
            finish()
            startActivity(intent)
        }

        val supervisorCardItems: Array<SupervisorCardData> = arrayOf<SupervisorCardData>(
            SupervisorCardData(
                "12345",
                "Pending",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "657894",
                "Accepted",
                "Chemical Engineering",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "098763",
                "Delivered",
                "Economic",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "098253",
                "Rejected",
                "Business",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "13455",
                "Pending",
                "Economic Arabic",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "767543",
                "Accepted",
                "Civil Engineering",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "13425",
                "Pending",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "422525",
                "Pending",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "135766",
                "Delivered",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "139866",
                "Delivered",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "185766",
                "Delivered",
                "Business",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "135799",
                "Accepted",
                "Civil Engineering",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "245766",
                "Rejected",
                "Economic Arabic",
                "05/04/2021",
                "Mohamed Ayman"
            ),
            SupervisorCardData(
                "135771",
                "Pending",
                "Computer Science",
                "05/04/2021",
                "Mohamed Ayman"
            ),
        )

        recyclerView = findViewById(R.id.rvSupervisorCardItems)
        var adapter = SupervisorCardAdapter(supervisorCardItems)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}