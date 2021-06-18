package com.scorpion_a.studentapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.fragments.HomeFragment
import com.scorpion_a.studentapp.fragments.MoreFragment
import com.scorpion_a.studentapp.fragments.NotificationsFragment
import com.scorpion_a.studentapp.fragments.RequestsPageFragment
import com.scorpion_a.studentapp.utils.Connection
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.MyPreferences
import com.scorpion_a.studentapp.utils.Theme


class HomeActivity : BaseActivity() {
     lateinit var  bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigation = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        Connection.isNetworkAvailable(this)


        var nav= intent.getIntExtra("menu", 0)
        if(nav==1){
            val moreFragment = MoreFragment.newInstance()
            openFragment(moreFragment)
            bottomNavigation.menu.findItem(R.id.more_tab).setChecked(true)

        }else {
            val homeFragment = HomeFragment.newInstance()
            openFragment(homeFragment)
        }

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

                val requestsFragment = RequestsPageFragment.newInstance()
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