package com.example.room.fragments

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.User
import com.example.room.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class addFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        view.btSave.setOnClickListener {
            insertToDB()
        }
        return view
    }

    private fun insertToDB() {
        val firstName = tvFirstName.text.toString()
        val lastName = tvLastName.text.toString()
        val age = tvAge.text.toString()

        val user = User(0, firstName, lastName, age)

        mUserViewModel.addUser(user)
        Toast.makeText(requireContext(), "Added Succesfully !!", Toast.LENGTH_SHORT).show()

        sendVibrations(200)
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

    private fun Fragment.sendVibrations(duration: Long) {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.EFFECT_CLICK))
    }
}