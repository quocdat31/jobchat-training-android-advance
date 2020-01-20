package com.example.androidadvance2.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.androidadvance2.fragments.AlbumsFragment
import com.example.androidadvance2.fragments.ArtistsFragment
import com.example.androidadvance2.fragments.PlaylistsFragment
import com.example.androidadvance2.fragments.SongsFragment

class FragmentAdapter(var context: Context, var fragmentManager: FragmentManager, var tabCount: Int) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SongsFragment()
            }
            1 -> {
                ArtistsFragment()
            }
            2-> {
                AlbumsFragment()
            }
            else -> {
                PlaylistsFragment()
            }
        }

    }

    override fun getCount(): Int {
        return tabCount
    }

}