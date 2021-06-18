package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.fragments.*
import com.scorpion_a.studentapp.utils.Connection
import com.scorpion_a.studentapp.utils.Lang
import com.scorpion_a.studentapp.utils.Theme

class StaffHomeActivity : BaseActivity() {
    lateinit var  bottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        Lang.loadLocate(this)
        Theme.checkTheme(this, delegate)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_home)
        Connection.isNetworkAvailable(this)

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