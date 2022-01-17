package com.kyonggi.roomdemo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kyonggi.roomdemo.Product
import com.kyonggi.roomdemo.databinding.ProductListItemBinding

class ProductListAdapter :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private var productList: List<Product>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = holder.item
        productList.let {
            item.text = it!![position].productName
        }
    }

    override fun getItemCount(): Int {
        return if (productList == null) 0 else productList!!.size
    }

    fun setProductList(products: List<Product>) {
        productList = products
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var item: TextView = binding.productRow
    }
}