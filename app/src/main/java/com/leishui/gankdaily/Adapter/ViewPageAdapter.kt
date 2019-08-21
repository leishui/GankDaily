package com.leishui.gankdaily.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.leishui.gankdaily.R

class ViewPageAdapter(private var context: Context,fm: FragmentManager, private val fragmentT: Fragment,private val fragmentF: Fragment) : FragmentPagerAdapter(
    fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return fragmentT
            1 -> return fragmentF
        }
        return fragmentT
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return context.getString(R.string.up_to_date)
            1->return context.getString(R.string.random)
        }
        return null
    }


}