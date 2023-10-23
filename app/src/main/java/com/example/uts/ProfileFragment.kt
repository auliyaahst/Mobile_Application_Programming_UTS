package com.example.uts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uts.data.ReferenceLink

class ProfileFragment : Fragment() {
    private var userName: String? = null
    private val referenceLinks = listOf(
        ReferenceLink("https://www.youtube.com/watch?v=qAHWVIK7_BY"),
        ReferenceLink("https://www.youtube.com/watch?v=WbpKInkd0YQ"),
        ReferenceLink("https://www.youtube.com/watch?v=SD097oVVrPE&t=359s"),
        ReferenceLink("https://developer.themoviedb.org/reference/movie-popular-list"),
        ReferenceLink("https://youtu.be/zOsWCAsG2Xo?si=Hmyu2WL4aHcLtLgz"),
        ReferenceLink("https://youtu.be/qAHWVIK7_BY?si=GcQpbolEFqo1-6mK"),
        ReferenceLink("https://chat.openai.com/"),
    )
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

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ReferenceListAdapter(referenceLinks)


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