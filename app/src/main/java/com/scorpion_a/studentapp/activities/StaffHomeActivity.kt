package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.fragments.*

class StaffHomeActivity : AppCompatActivity() {
    lateinit var  bottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_home)
        bottomNavigation = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val staffHomeFragment = StaffHomeFragment.newInstance()
        openFragment(staffHomeFragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.staff_home_tab -> {
                val staffHomeFragment = StaffHomeFragment.newInstance()
                openFragment(staffHomeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.staff_menu_tab -> {
                val staffMenuFragment = StaffMenuFragment.newInstance()
                openFragment(staffMenuFragment)
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