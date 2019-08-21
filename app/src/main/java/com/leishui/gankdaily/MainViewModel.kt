package com.leishui.gankdaily

import android.app.Application
import android.view.MenuItem
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.leishui.gankdaily.Util.FragmentUtil

class MainViewModel(): ViewModel() {
    var title:String = "Android"

    fun setTitle(item: MenuItem){
        title = FragmentUtil.getFragment(item).toString()
    }
}
