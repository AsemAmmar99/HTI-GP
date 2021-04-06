package com.scorpion_a.studentapp.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.activities.*
import kotlinx.android.synthetic.main.fragment_staff_home.view.*

class StaffHomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_staff_home, container, false)

        view.clAccepted.setOnClickListener {
            val intent = Intent(it.context, AcceptedRequestsActivity::class.java)
            startActivity(intent)
        }

        view.clPending.setOnClickListener {
            val intent = Intent(it.context, PendingRequestsActivity::class.java)
            startActivity(intent)
        }

        view.clRejected.setOnClickListener {
            val intent = Intent(it.context, RejectedRequestsActivity::class.java)
            startActivity(intent)
        }

        view.clDelivered.setOnClickListener {
            val intent = Intent(it.context, DeliveredRequestsActivity::class.java)
            startActivity(intent)
        }

        view.clSearch.setOnClickListener {
            val intent = Intent(it.context, SearchForRequestsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {
        fun newInstance(): StaffHomeFragment = StaffHomeFragment()
    }
}