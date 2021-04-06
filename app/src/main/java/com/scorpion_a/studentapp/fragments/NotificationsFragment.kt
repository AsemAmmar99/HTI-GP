package com.scorpion_a.studentapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scorpion_a.studentapp.adapters.NotificationListAdapter
import com.scorpion_a.studentapp.model.NotificationListData
import com.scorpion_a.studentapp.R
import kotlinx.android.synthetic.main.fragment_notification.view.*

class NotificationsFragment  : Fragment() {
    lateinit var  recyclerView: RecyclerView
    lateinit var toolbar: Toolbar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_notification, container, false)
        toolbar=view.header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.notifications)

        val notificationListData: Array<NotificationListData> = arrayOf<NotificationListData>(
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 4:30 PM"
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 13 May"
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 13 May"
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 13 May"
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 18 Oct."
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 18 Oct."
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 18 Oct."
            ),
            NotificationListData(
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "Higher Technological Institute approved DummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummyDummy",
                "\u25A0 18 Oct."
            )
        )

        recyclerView = view.findViewById(R.id.rvNotification)
        val adapter = NotificationListAdapter(
            notificationListData, view.context
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        return  view
    }
    companion object {
        fun newInstance(): NotificationsFragment = NotificationsFragment()
    }
}
