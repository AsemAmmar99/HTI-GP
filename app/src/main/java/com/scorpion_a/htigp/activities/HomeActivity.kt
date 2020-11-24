package com.scorpion_a.htigp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.scorpion_a.htigp.R
import com.scorpion_a.htigp.fragments.HomeFragment
import com.scorpion_a.htigp.fragments.MoreFragment
import com.scorpion_a.htigp.fragments.NotificationsFragment
import com.scorpion_a.htigp.fragments.RequestsFragment

class HomeActivity : AppCompatActivity() {
     lateinit var  bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigation = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val homeFragment = HomeFragment.newInstance()
        openFragment(homeFragment)

    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home_tab -> {
                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.notifications_tab -> {
                val notificationsFragment = NotificationsFragment.newInstance()
                openFragment(notificationsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.requests_tab -> {

                val requestsFragment = RequestsFragment.newInstance()
                openFragment(requestsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.more_tab -> {
                val moreFragment = MoreFragment.newInstance()
                openFragment(moreFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.popBackStack()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}