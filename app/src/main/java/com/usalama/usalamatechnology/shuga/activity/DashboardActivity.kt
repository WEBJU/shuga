package com.usalama.usalamatechnology.shuga.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.usalama.usalamatechnology.shuga.BaseActivity
import com.usalama.usalamatechnology.shuga.R
import com.usalama.usalamatechnology.shuga.activity.ui.ProfileFragment
import com.usalama.usalamatechnology.shuga.activity.ui.SubscriptionFragment
import com.usalama.usalamatechnology.shuga.activity.ui.dashboard.DashboardFragment
import com.usalama.usalamatechnology.shuga.activity.ui.home.HomeFragment
import com.usalama.usalamatechnology.shuga.activity.ui.notifications.NotificationsFragment
import com.usalama.usalamatechnology.shuga.databinding.ActivityDashboardBinding
import com.usalama.usalamatechnology.shuga.utils.*

class DashboardActivity : BaseActivity() {

    private lateinit var binding: ActivityDashboardBinding
    var homeFragment = DashboardFragment()
    var exploreFragment = HomeFragment()
    var messageFragment = NotificationsFragment()
    var profileFragment = ProfileFragment()
    var subscriptionFragment = SubscriptionFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  lightStatusBar(color(R.color.da_white))
        replaceFragment(homeFragment, R.id.frameContainer)

        val navView: BottomNavigationView = binding.navView

//        val navController = findNavController(R.id.nav_host_fragment_activity_dashboard)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
        navView.setSelectedItemId(R.id.navigation_home);

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(homeFragment, R.id.frameContainer)

                }
                R.id.navigation_matches -> {
                    replaceFragment(exploreFragment, R.id.frameContainer)

                }
                R.id.navigation_messages -> {
                    replaceFragment(messageFragment, R.id.frameContainer)

                }
                R.id.subscription -> {
                    replaceFragment(subscriptionFragment, R.id.frameContainer)
                }
                R.id.profile -> {
                    replaceFragment(profileFragment, R.id.frameContainer)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        navView.itemIconTintList = null

    }
}