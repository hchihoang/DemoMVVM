package hn9x.techz.demomvvm.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import hn9x.techz.demomvvm.R
import hn9x.techz.demomvvm.model.RepositoriesEntity
import kotlinx.android.synthetic.main.itemlistview.view.*

class Adapter_List (private val activity: Activity, countries: ArrayList<RepositoriesEntity>) : BaseAdapter(){
    private var listArray = ArrayList<RepositoriesEntity>()

    init {
        this.listArray = countries
    }

    override fun getCount(): Int {
        return listArray.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater :LayoutInflater = LayoutInflater.from(activity)
        val view : View = layoutInflater.inflate(R.layout.itemlistview , null )
        view.tvName.text = listArray[position].name + "\n" + listArray[position].full_name
        return view
    }
}