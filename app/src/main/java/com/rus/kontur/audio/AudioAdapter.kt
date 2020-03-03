package com.rus.kontur.audio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TwoLineListItem
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rus.kontur.data.Audio

class AudioAdapter(private val audioViewModel: AudioViewModel) : ListAdapter<Audio, AudioAdapter.AudioViewHolder>(AudioCallback()) {

    class AudioViewHolder(private val v: View): RecyclerView.ViewHolder(v){
        fun bind(audio: Audio) {
            (v as TwoLineListItem).findViewById<TextView>(android.R.id.text1).text = audio.title
            v.findViewById<TextView>(android.R.id.text2).text = audio.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return AudioViewHolder(view)
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        if(audioViewModel.audio.value != null) {
            holder.bind(audioViewModel.audio.value!![position])
        }
    }

    override fun getItemId(position: Int): Long {
        return if (audioViewModel.audio.value != null) {
            audioViewModel.audio.value!![position].id.toLong()
        } else {
            0
        }
    }

    override fun getItemCount(): Int {
        return if(audioViewModel.audio.value != null) {
            audioViewModel.audio.value!!.size
        } else {
            0
        }
    }
}

class AudioCallback : DiffUtil.ItemCallback<Audio>() {
    override fun areItemsTheSame(oldItem: Audio, newItem: Audio): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Audio, newItem: Audio): Boolean {
        return oldItem == newItem
    }

}