package com.aplikasi.crudkotlin.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.crudkotlin.R

class Adapter : RecyclerView.Adapter<Adapter.MyHolderView>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(data: com.aplikasi.crudkotlin.model.User)
    }

    var listUser = ArrayList<com.aplikasi.crudkotlin.model.User>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            listUser.clear()
            listUser.addAll(value)
            notifyDataSetChanged()
        }

    inner class MyHolderView (itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val name : TextView = itemView.findViewById(R.id.ti_name)
        val addr : TextView = itemView.findViewById(R.id.ti_address)
        val age : TextView = itemView.findViewById(R.id.ti_age)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyHolderView {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_user, viewGroup, false)
        return MyHolderView(itemView)
    }

    override fun onBindViewHolder(holder: MyHolderView, position: Int) {
        val user : com.aplikasi.crudkotlin.model.User = listUser[position]
        holder.name.text = user.strName
        holder.addr.text = user.strAddress
        holder.age.text = user.intAge.toString()
    }

    override fun getItemCount(): Int = listUser.size
}