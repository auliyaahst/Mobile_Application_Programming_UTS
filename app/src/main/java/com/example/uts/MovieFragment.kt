package com.example.uts

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uts.api.MovieApiService
import com.example.uts.data.Movie
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MovieFragment : Fragment() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    private val movieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }

    private lateinit var apiResponseView: TextView
    private val movies = ArrayList<Movie>()
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        apiResponseView = view.findViewById(R.id.api_response)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = MovieAdapter(movies)
        getMovieResponse()
    }

    private fun getMovieResponse() {
        val call = movieApiService.searchMovies()
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    // Parse JSON response using Moshi and add data to the movies list
                    val json = response.body()
                    val moshi = Moshi.Builder().build()
                    val adapter = moshi.adapter(Movie::class.java)
                    val movie = adapter.fromJson(json)
                    if (movie != null) {
                        movies.add(movie)
                        recyclerView.adapter?.notifyItemInserted(movies.size - 1)
                    }
                } else {
                    Log.e(
                        MOVIE_ACTIVITY, "Failed to get response\n" +
                                response.errorBody()?.string().orEmpty()
                    )
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(MOVIE_ACTIVITY, "Failed to get response", t)
            }
        })
    }

    companion object {
        const val MOVIE_ACTIVITY = "MOVIE_ACTIVITY"
    }
}