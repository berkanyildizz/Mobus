package com.berkan.mobus_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berkan.mobus_10.databinding.RcRowYolculistBinding

class yolculistrcadapter(private val yolculist: ArrayList<yolcudataclass>) :RecyclerView.Adapter<yolculistrcadapter.yolcuholder>(){

    class yolcuholder(val binding: RcRowYolculistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): yolcuholder {
        val binding = RcRowYolculistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return yolcuholder(binding)
    }

    override fun getItemCount(): Int {
        return yolculist.size
    }

    override fun onBindViewHolder(holder: yolcuholder, position: Int) {
        val currentYolcu = yolculist[position]
        holder.binding.adsoyadrow.text = currentYolcu.adsoyad
        // Diğer veri alanlarını burada bağlayın
        holder.binding.koltuknorow.text = currentYolcu.koltukno
        holder.binding.neredennereyerow.text="${currentYolcu.nereden} ---> ${currentYolcu.nereye}"
        holder.binding.sefernorow.text=currentYolcu.seferno
        /*
        holder.binding.neredenrow.text = currentYolcu.nereden
        holder.binding.nereyerow.text = currentYolcu.nereye
        holder.binding.sefernorow.text = currentYolcu.seferno
        holder.binding.useremailrow.text = currentYolcu.userEmail
        holder.binding.daterow.text = currentYolcu.date
        */

    }
}



