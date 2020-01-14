package com.example.androidadvance.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.androidadvance.fragment.FirstFragment
import com.example.androidadvance.fragment.FourthFragment
import com.example.androidadvance.fragment.SecondFragment
import com.example.androidadvance.fragment.ThirdFragment

class FragmentAdapter(var context: Context, fragmentManager: FragmentManager, var tabCount: Int): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FirstFragment()
            }
            1 -> {
                SecondFragment()
            }
            2 -> {
                ThirdFragment()
            }
            else -> FourthFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}
