package com.example.room.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.UserViewModel
import com.example.room.adapter.UserAdapter
import kotlinx.android.synthetic.main.fragment_list.view.*

class listFragment : Fragment() {
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
        userAdapter = UserAdapter()
        view.recyclerList.apply {
            adapter = userAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { data ->
            userAdapter?.updateData(data)
        })

        return view
    }
}