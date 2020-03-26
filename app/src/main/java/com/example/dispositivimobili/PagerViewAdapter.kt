package com.example.dispositivimobili

import HomeFragment
import NotificationFragment
import ProfileFragment
import SearchFragment
import android.app.Notification
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


internal class PagerViewAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                HomeFragment()
            }

            1 -> {
                SearchFragment()

            }

            2 -> {
                NotificationFragment()

            }

            3 -> {
                ProfileFragment()

            }
            else -> null
        }
    }

    override fun getCount(): Int {
        return 4
    }




}

