package com.leishui.gankdaily.Util

import android.view.MenuItem
import com.leishui.gankdaily.R

object FragmentUtil {
    fun getFragment(item: MenuItem): String? {
        when(item.itemId){
            R.id.android_fragment -> return "Android"
            R.id.ios_fragment-> return "iOS"
            R.id.welfare_fragment->return "福利"
            R.id.all_fragment->return "all"
            R.id.app_fragment->return "App"
            R.id.expanding_resources_fragment->return "拓展资源"
            R.id.recommend_fragment->return "瞎推荐"
            R.id.video_fragment->return "休息视频"
            R.id.web_front_fragment->return "前端"
        }
        return null
    }
}