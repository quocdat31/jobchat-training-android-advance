package com.example.androidadvance2.fragments

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvance2.*
import com.example.androidadvance2.adapter.OnItemClickListener
import com.example.androidadvance2.adapter.SongsAdapter
import com.example.androidadvance2.model.Songs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_songs.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SongsFragment : Fragment(), OnItemClickListener {

    private var isPlaying: Boolean = false
    private val REQUEST_CODE = 101
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_songs, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var context = activity!!.applicationContext

        if (mCheckPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            GetSongList(loadingSongsProgressBar, context, musicRecyclerView, this).execute()

        }
        else {
            mRequestPermission(activity!!, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
        }

        playPauseMediaButton.setOnClickListener {
            isPlaying  = !isPlaying
            setImageSrc(playPauseMediaButton)
            setImageSrc(playPauseMediaButton2)

        }

        playPauseMediaButton2.setOnClickListener {
            isPlaying  = !isPlaying
            setImageSrc(playPauseMediaButton2)
            setImageSrc(playPauseMediaButton)
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SongsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onItemClick(songs: Songs) {

        isPlaying = true

        setImageSrc(playPauseMediaButton)
        setImageSrc(playPauseMediaButton2)

        showUpBottomSheet(songs, bottomSheet)

        var mediaPlayer = activity?.applicationContext?.let { MyMediaPlayer(MediaPlayer(), it, songs.uri, seekBar, playPauseMediaButton2) }

        mediaPlayer?.play(currentTimeTextView)

    }

    private fun showUpBottomSheet(songs: Songs, view: View) {

        songTitleBottomSheetTextView.text = songs.title
        songArtistBottomSheetTextView.text = songs.artist

        var bottomSheetBehavior = BottomSheetBehavior.from(view)
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun onPlayPauseButtonClick() {

    }

    private fun setImageSrc(imageView: ImageView) {
        if (isPlaying) {
            imageView.setImageResource(R.drawable.ic_pause_black_24dp)
        } else {
            imageView.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        }
    }

    fun onPlayButtonClick() {

        if (isPlaying) {  }

        isPlaying  = !isPlaying
        setImageSrc(playPauseMediaButton)
        setImageSrc(playPauseMediaButton2)
    }
}
