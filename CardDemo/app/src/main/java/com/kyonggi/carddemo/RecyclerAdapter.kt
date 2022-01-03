package com.kyonggi.carddemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kyonggi.carddemo.databinding.CardLayoutBinding

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {

    private val titles = arrayOf("Chapter One", "Chapter Two", "Chapter Three", "Chapter Four",
        "Chapter Five", "Chapter Six", "Chapter Seven", "Chapter Eight")

    private val details = arrayOf("Item one details", "Item two details", "Item three details",
        "Item four details", "Item five details", "Item six details", "Item seven details", "Item eight details")

    private val images = intArrayOf(R.drawable.android_image_1, R.drawable.android_image_2, R.drawable.android_image_3,
        R.drawable.android_image_4, R.drawable.android_image_5, R.drawable.android_image_6, R.drawable.android_image_7,
        R.drawable.android_image_8)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    // 매개변수 holder position -> 뷰에 데이터를 참조
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTitle(titles[position])
        holder.bindDetail(details[position])
        holder.bindImage(images[position])

        holder.itemView.setOnClickListener {
            Snackbar.make(holder.itemView, "Click detected on item $position",
            Snackbar.LENGTH_SHORT).setAction("Action", null).show()
        }
    }
    override fun getItemCount() =  titles.size

    /*
    ViewHolder 클래스 -> card_view사용
     */
    inner class CustomViewHolder(private val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTitle(s: String) {
            binding.itemTitle.text = s
        }

        fun bindDetail(s: String) {
            binding.itemDetail.text = s
        }

        fun bindImage(image: Int) {
            binding.itemImage.setImageResource(image)
        }

    }
}