package com.example.androidadvance.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvance.R
import com.example.androidadvance.adapter.HeroAdapter
import com.example.androidadvance.model.Hero
import kotlinx.android.synthetic.main.fragment_third.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ThirdFragment : Fragment() {
    private var mDataList: ArrayList<Hero> = ArrayList()
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
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addItemTomDataList()

        heroRecyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        heroRecyclerView.adapter = HeroAdapter(mDataList, activity!!.applicationContext)
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
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
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun addItemTomDataList(){
        mDataList.add(Hero(R.drawable.batman, "Batman"))
        mDataList.add(Hero(R.drawable.captain, "Captain"))
        mDataList.add(Hero(R.drawable.thor, "Thor"))
        mDataList.add(Hero(R.drawable.superman, "Superman"))
        mDataList.add(Hero(R.drawable.wolverine, "Wolverine"))
        mDataList.add(Hero(R.drawable.superman, "Who the fck is this guy"))
        mDataList.add(Hero(R.drawable.wolverine, "Another hero"))
        mDataList.add(Hero(R.drawable.batman, "Batman"))
        mDataList.add(Hero(R.drawable.captain, "Captain"))
        mDataList.add(Hero(R.drawable.thor, "Thor"))
        mDataList.add(Hero(R.drawable.superman, "Superman"))
        mDataList.add(Hero(R.drawable.wolverine, "Wolverine"))
        mDataList.add(Hero(R.drawable.superman, "Who the fck is this guy"))
        mDataList.add(Hero(R.drawable.wolverine, "Another hero"))
        mDataList.add(Hero(R.drawable.batman, "Batman"))
        mDataList.add(Hero(R.drawable.captain, "Captain"))
        mDataList.add(Hero(R.drawable.thor, "Thor"))
        mDataList.add(Hero(R.drawable.superman, "Superman"))
        mDataList.add(Hero(R.drawable.wolverine, "Wolverine"))
        mDataList.add(Hero(R.drawable.superman, "Who the fck is this guy"))
        mDataList.add(Hero(R.drawable.wolverine, "Another hero"))
    }
}
