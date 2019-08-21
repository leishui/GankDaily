package com.leishui.gankdaily

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.leishui.gankdaily.Fragment.BaseFragment
import com.leishui.gankdaily.Fragment.SearchResultFragment
import com.leishui.gankdaily.Util.ThreadUtil
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.load_more.*

class SearchActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setSupportActionBar(result_toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_24dp)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.item_menu_search)
        searchView = searchItem?.actionView as SearchView
        searchView.onActionViewExpanded()
        searchView.maxWidth = getWidth()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun search(query: String?) {
        val searchResultFragment = query?.let { SearchResultFragment(it) } as BaseFragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.result_fragment, searchResultFragment, searchResultFragment.toString())
        transaction.commit()
    }

    private fun getWidth(): Int {
        val point = Point()
        windowManager.defaultDisplay.getSize(point)
        return point.x
    }
}
