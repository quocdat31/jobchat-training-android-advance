package com.example.androidadvance.fragment

import android.Manifest.permission.*
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.androidadvance.BuildConfig
import com.example.androidadvance.R
import com.example.androidadvance.helper.checkPermission
import kotlinx.android.synthetic.main.fragment_first.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FirstFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mPickImageFromGallery: Boolean = false
    private var mCurrentPhotoPath: String? = null
    private val PERMISSION_REQUEST_CODE: Int = 101
    private val REQUEST_IMAGE_CAPTURE = 1
    private val SELECT_FILE = 1
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
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val context = activity!!.applicationContext

        searchButton.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.jobchat.vn/")
            startActivity(openURL)
        }

        cameraButton.setOnClickListener {
            mPickImageFromGallery = false
            if(checkPermission(context, CAMERA) && checkPermission(context, READ_EXTERNAL_STORAGE)) takePicture()
            else requestPermissions(arrayOf(READ_EXTERNAL_STORAGE, CAMERA), PERMISSION_REQUEST_CODE)
        }

        imageView.setOnClickListener{
            mPickImageFromGallery = true
            if(checkPermission(context, READ_EXTERNAL_STORAGE)) accessGallery() else requestPermissions(
                arrayOf(READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
        }

        phoneNumberEditText.setOnClickListener{
            if(checkPermission(context, CALL_PHONE)) callPhone() else requestPermissions(arrayOf(CALL_PHONE), 42)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE)
            {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    takePicture()
                } else {
                    Toast.makeText(activity!!.applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        else if (requestCode == 42) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                callPhone()
            } else {
                Toast.makeText(activity!!.applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
            return
        }
        }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException("$context must implement OnFragmentInteractionListener")
//        }
//    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun createFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile("JPEG_${timeStamp}",".jpg", storageDir).apply { mCurrentPhotoPath = absolutePath }
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file: File = createFile()
        val uri: Uri = FileProvider.getUriForFile(
            activity!!.applicationContext, "${BuildConfig.APPLICATION_ID}.provider", file
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            if (mPickImageFromGallery) {
            val imgUri = data!!.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, imgUri)
                imageView.setImageBitmap(bitmap)
            }
            catch (e: IOException) {e.printStackTrace()}
            } else {
                var bitmap: Bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    private fun accessGallery() {
        val intent = Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent,"Select File"), SELECT_FILE)
    }

    private fun callPhone(){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${phoneNumberEditText.text}"))
        startActivity(intent)
    }

}
