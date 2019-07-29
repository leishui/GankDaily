package com.leishui.gankdaily.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leishui.gankdaily.Adapter.BaseAdapter
import com.leishui.gankdaily.DisplayActivity
import com.leishui.gankdaily.R
import com.leishui.gankdaily.ResultBean.AndroidResult
import com.leishui.gankdaily.Util.ThreadUtil
import com.leishui.gankdaily.Util.UrlUtil
import kotlinx.android.synthetic.main.fragment.*
import okhttp3.*
import java.io.IOException

open class BaseFragment(var type: String) : Fragment() {
    var page = 1
    val adapter by lazy { BaseAdapter() }
    val swipeRefreshLayout: SwipeRefreshLayout by lazy { swiperefresh }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
        initData(type)
        swipeRefreshLayout.setOnRefreshListener { initData(type) }
        recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager){
                        val manager = layoutManager
                        val lastPosition = manager.findLastVisibleItemPosition()
                        if (lastPosition == adapter.itemCount-1)
                            loadMore(10)
                    }
                }
            }
        })
        adapter.setOnItemClickListener(object :BaseAdapter.OnItemClickListener{
            override fun onItemClick(list: List<AndroidResult.ResultsBean>, position: Int) {
                var intent = Intent(context,DisplayActivity::class.java)
                intent.putExtra("url",list[position].url)
                startActivity(intent)
            }

        })
    }

    //初始数据
    private fun initData(type:String) {
        val client = OkHttpClient()
        val url = UrlUtil.getPath(type,10,1)
        val request = Request.Builder().url(url).get().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                val gson = Gson()
                val androidResult = gson.fromJson<AndroidResult>(result, object : TypeToken<AndroidResult>() {}.type)
                swipeRefreshLayout.isRefreshing = false
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        adapter.updateList(androidResult.results)
                        //Toast.makeText(context,"获取数据成功",Toast.LENGTH_SHRORT).show()
                    }
                })
            }

            override fun onFailure(call: Call, e: IOException) {
                swiperefresh?.isRefreshing = false
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        Toast.makeText(context,"获取数据失败:网络连接失败\n$e",Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })
    }

    //加载更多
    private fun loadMore(num:Int){
        val client = OkHttpClient()
        val url = UrlUtil.getPath(type,num,page++)
        val request = Request.Builder().url(url).get().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                val gson = Gson()
                val androidResult = gson.fromJson<AndroidResult>(result, object : TypeToken<AndroidResult>() {}.type)
                ThreadUtil.runOnMainThread(Runnable {
                    adapter.loadMore(androidResult.results)
                    //Toast.makeText(context,"加载数据成功",Toast.LENGTH_LONG).show()
                })
            }

            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable { Toast.makeText(context,"加载数据失败:网络连接失败$e",Toast.LENGTH_LONG).show() })
            }
        })
    }
}