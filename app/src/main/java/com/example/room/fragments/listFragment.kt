package com.example.room.fragments

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.UserViewModel
import com.example.room.adapter.UserAdapter
import kotlinx.android.synthetic.main.fragment_list.view.*

class listFragment : Fragment(), UserAdapter.ClickListener {
    private lateinit var mUserViewModel: UserViewModel
    private var userAdapter: UserAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userAdapter = UserAdapter(this)

        view.recyclerList.apply {
            adapter = userAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { data ->
            userAdapter?.updateData(data)
        })

        view.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    getDataFromDb(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    getDataFromDb(newText)
                }
                return true
            }
        })

        return view
    }

    private fun getDataFromDb(searchText: String) {
        var searchText = searchText
        searchText = "%$searchText%"

        mUserViewModel.searchUser(searchText = searchText)?.observe(viewLifecycleOwner) { list ->
            list?.let {
                Log.e("List = ", list.toString())
            }

        }

    }

    override fun onClick(id: Int) {
        mUserViewModel.deleteUser(id)
        sendVibrations(100)
    }

    private fun Fragment.sendVibrations(duration: Long) {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}