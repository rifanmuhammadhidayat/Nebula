package org.d3if3084.nebula.ui.zodiak

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3084.nebula.R
import org.d3if3084.nebula.databinding.ListItemBinding
import org.d3if3084.nebula.model.Zodiak
import org.d3if3084.nebula.network.ServiceAPI

class ZodiakAdapter : RecyclerView.Adapter<ZodiakAdapter.ViewHolder>(){

    private val data = mutableListOf<Zodiak>()

    fun updateData(newData: List<Zodiak>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(zodiak : Zodiak) = with(binding){
            judulTextView.text = zodiak.judul
            tglTextView.text = zodiak.tgl
            Glide.with(imageView.context)
                .load(ServiceAPI.getZodiakUrl(zodiak.imageId))
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)
            tempatTextView.text = zodiak.tempat
            deskripsiTextView.text = zodiak.deskripsi


            root.setOnClickListener{
                val message = root.context.getString(R.string.message, zodiak.judul)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}