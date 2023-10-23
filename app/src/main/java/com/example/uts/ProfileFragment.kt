package com.example.uts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProfileFragment : Fragment() {
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userName = it.getString("user_name")
        }
        Log.d("ProfileFragment", "userName: $userName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val profileNameTextView = view.findViewById<TextView>(R.id.profile_name)
        if (userName != null) {
            profileNameTextView.text = userName
        }

        return view
    }

    companion object {
        fun newInstance(userName: String?) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString("user_name", userName)
            }
        }
    }
}