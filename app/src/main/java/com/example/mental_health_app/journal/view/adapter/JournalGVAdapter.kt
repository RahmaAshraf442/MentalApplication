package com.example.notesapp.notes.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat.getSystemService
import com.example.mental_health_app.R
import com.example.mental_health_app.journal.model.JournalImagesModel

class JournalGVAdapter(private val imagesList: List<JournalImagesModel>,private val context: Context)
    : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var image: ImageView

    override fun getCount(): Int {
        return imagesList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.journal_thoughts_item, null)
        }
        image = convertView!!.findViewById(R.id.iv_viewed_image)
        image.setImageResource(imagesList.get(position).journalImage)
        return convertView
    }
}