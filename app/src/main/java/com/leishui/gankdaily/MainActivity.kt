package com.leishui.gankdaily

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import com.leishui.gankdaily.Adapter.ViewPageAdapter
import com.leishui.gankdaily.Fragment.BaseFragment
import com.leishui.gankdaily.Util.FragmentUtil
import com.leishui.gankdaily.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.content_main.tabLayout
import kotlinx.android.synthetic.main.content_main.viewPage

class MainActivity : AppCompatActivity() {

    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var adapter: ViewPageAdapter
    private lateinit var type:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //val model = ViewModelProviders.of(this)[MainViewModel::class.java]
        val baseFragment1 = BaseFragment("Android",true)
        val baseFragment2 = BaseFragment("Android",false)

        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView
        toolbar = binding.toolbar


        adapter = ViewPageAdapter(this,supportFragmentManager,baseFragment1,baseFragment2)
        viewPage.adapter = adapter
        tabLayout.setupWithViewPager(viewPage)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }


        navigationView.setNavigationItemSelectedListener {
//            val transaction = supportFragmentManager.beginTransaction()
//            model.getFragment(it)?.let { it1 -> transaction.replace(R.id.container, it1, it.toString()) }
//            transaction.commit()
//            toolbar.title = model.title
            type = FragmentUtil.getFragment(it).toString()
            binding.mainTitle.text = type
            baseFragment1.type = type
            baseFragment1.initData("")
            baseFragment2.type = type
            baseFragment2.initData("")
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->drawerLayout.openDrawer(GravityCompat.START)
            R.id.item_menu_main_search->startActivity(Intent(this,SearchActivity::class.java))
        }
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

}
