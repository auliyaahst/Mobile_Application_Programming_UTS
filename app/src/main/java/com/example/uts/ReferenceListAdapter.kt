package com.example.uts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uts.data.ReferenceLink

class ReferenceListAdapter(private val referenceLinks: List<ReferenceLink>) :
    RecyclerView.Adapter<ReferenceListAdapter.ReferenceLinkViewHolder>() {

    inner class ReferenceLinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val referenceLinkText: TextView = itemView.findViewById(R.id.referenceLink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReferenceLinkViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_reference, parent, false)
        return ReferenceLinkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReferenceLinkViewHolder, position: Int) {
        val currentLink = referenceLinks[position]
        holder.referenceLinkText.text = currentLink.link
    }

    override fun getItemCount(): Int {
        return referenceLinks.size
    }
}
