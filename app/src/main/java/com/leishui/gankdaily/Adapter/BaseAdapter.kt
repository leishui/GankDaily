package com.leishui.gankdaily.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leishui.gankdaily.R
import com.leishui.gankdaily.ResultBean.AndroidResult
import kotlinx.android.synthetic.main.item.view.*

class BaseAdapter: RecyclerView.Adapter<BaseAdapter.RecyclerHolder>() {
    var list = ArrayList<AndroidResult.ResultsBean>()

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }


    interface OnItemClickListener{
        fun onItemClick(list: List<AndroidResult.ResultsBean>, position: Int)
    }


    //刷新 初始数据
    fun updateList(list: List<AndroidResult.ResultsBean>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    //加载更多数据
    fun loadMore(list: List<AndroidResult.ResultsBean>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (position == list.size)
            return 1
        else
            return 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        if (viewType == 0)
            return RecyclerHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item,
                    parent,
                    false
                )
            )
        else
            return RecyclerHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.load_more,
                    parent,
                    false
                )
            )
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        if (position == list.size)
            return
        var data = list[position]
        if (data.type=="\u798f\u5229"){
            Glide.with(holder.itemView).load(data.url).into(holder.img)
        }else{
            holder.item.setOnClickListener {
                onItemClickListener.onItemClick(list,position)
            }
            holder.img.isVisible = false
            holder.des.text = data.desc
        }
        holder.time.text = data.createdAt
        holder.who.text = data.who
    }

    class RecyclerHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val img = itemView.img
        val des = itemView.des
        val time = itemView.time
        val who = itemView.who
        val item = itemView.list_item
    }

}