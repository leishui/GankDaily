package com.leishui.gankdaily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.leishui.gankdaily.Fragment.AndroidFragment
import com.leishui.gankdaily.Fragment.BaseFragment
import com.leishui.gankdaily.Fragment.IosFragment
import com.leishui.gankdaily.Fragment.WelfareFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottombar.setOnTabSelectListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, getFragment(it), it.toString())
            transaction.commit()
        }
    }

    fun getFragment(id: Int): Fragment {
        when (id) {
            R.id.tab_android -> return AndroidFragment()
            R.id.tab_ios -> return IosFragment()
            R.id.tab_welfare -> return WelfareFragment()
        }
        return AndroidFragment()
    }
}
