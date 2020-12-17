package com.scorpion_a.htigp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.htigp.R
import com.scorpion_a.htigp.adapters.FAQsListAdapter
import kotlinx.android.synthetic.main.activity_profile_page.*
import com.scorpion_a.htigp.model.FAQsListData
import kotlinx.android.synthetic.main.activity_faqs.*
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_profile_page.header

class FAQSActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var  recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs)
        toolbar=header.findViewById(R.id.toolbar)
            toolbar.title="FAQs"

        tvSupport.setOnClickListener {
            val intent = Intent(this, SupportActivity::class.java)
            startActivity(intent)
        }

        val faqsData: Array<FAQsListData> = arrayOf<FAQsListData>(
            FAQsListData(
                "ما هي مدة الدراسة في المعهد؟"
            ),
            FAQsListData(
                "ما هي مدة الدراسة في المعهد؟"
            ),
            FAQsListData(
                "ما هي مدة الدراسة في المعهد؟"
            ),
            FAQsListData(
                "ما هي مدة الدراسة في المعهد؟"
            ),
            FAQsListData(
                "ما هي مدة الدراسة في المعهد؟"
            ),
            FAQsListData(
                "ما هي مدة الدراسة في المعهد؟"
            )
        )

        recyclerView = findViewById(R.id.rvFAQs)
        var adapter = FAQsListAdapter(faqsData, this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}