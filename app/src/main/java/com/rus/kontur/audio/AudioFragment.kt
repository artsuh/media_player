package com.rus.kontur.audio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.rus.kontur.R
import com.rus.kontur.data.Audio
import com.rus.kontur.MainApplication
import com.rus.kontur.util.getViewModelFactory

class AudioFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    var audioList: RecyclerView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null

    companion object {
        fun newInstance() = AudioFragment()
    }

    private lateinit var viewModel: AudioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.audio_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        audioList = view!!.findViewById(R.id.audioList)
        swipeRefreshLayout = view!!.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout!!.setOnRefreshListener(this)
        swipeRefreshLayout!!.setColorSchemeResources(R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark);
        viewModel = getViewModelFactory().create(AudioViewModel::class.java)
        audioList!!.layoutManager = LinearLayoutManager(context)
        val audioAdapter = AudioAdapter(viewModel)
        audioAdapter.setHasStableIds(true)
        audioList!!.adapter = audioAdapter
        viewModel.audio.observe(
            viewLifecycleOwner, Observer<List<Audio>> {
                Log.d(MainApplication.TAG, "audio was changed")
                swipeRefreshLayout!!.isRefreshing = false
                audioAdapter.notifyDataSetChanged()
            }
        )
//        for the first time -> swipeRefreshLayout.post
    }

    override fun onRefresh() {
        Log.d(MainApplication.TAG, "onRefresh")
        viewModel.loadAudio(true)
    }

}
