package com.example.mental_health_app.yoga.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mental_health_app.databinding.YogaRowBinding
import com.example.mental_health_app.yoga.model.YogaModel
import com.example.notesapp.notes.model.NoteModel

class YogaAdapter(private val onItemClicked: (Int) -> Unit ): RecyclerView.Adapter<YogaViewHolder>()  {

    private var yogaList = mutableListOf<YogaModel>()
    fun setYoga(yoga: List<YogaModel>) {
        yogaList = yoga.toMutableList()
        notifyDataSetChanged()
    }
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YogaViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = YogaRowBinding.inflate(inflater, parent, false)
        return YogaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YogaViewHolder, position: Int) {
        val item = yogaList[position]
        holder.binding.tvYogaName.text=item.yogaName
        holder.binding.tvYogaDescription.text=item.yogaDescription
        holder.binding.ivYoga.setImageResource(item.yogaImage)
        holder.itemView.setOnClickListener{
            onItemClicked(item.id)
        }
    }

    override fun getItemCount(): Int {
        return yogaList.size
    }
}


class YogaViewHolder(val binding: YogaRowBinding) : RecyclerView.ViewHolder(binding.root) {

}