package com.okta.moviecatalogue.ui.movie


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okta.moviecatalogue.BuildConfig
import com.okta.moviecatalogue.R
import com.okta.moviecatalogue.model.movielist.ResultListMovie
import com.okta.moviecatalogue.networking.Service
import com.okta.moviecatalogue.ui.adapter.RVMovieAdapter
import com.okta.moviecatalogue.ui.detail.DetailActivity
import com.okta.moviecatalogue.ui.home.HomeFragment
import com.okta.moviecatalogue.utils.BaseAppFragment
import com.okta.moviecatalogue.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseAppFragment() {

    private lateinit var adapter: RVMovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var recyclerView: RecyclerView
    @Inject
    lateinit var service: Service


    companion object {

        private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deps.inject(this)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.movies.observe(viewLifecycleOwner,getMovies)
        movieViewModel.setMovies(BuildConfig.TSDB_API_KEY,"en-US", service)

        pb_movie_fragment.visibility = View.VISIBLE

    }

    private val getMovies = Observer<List<ResultListMovie>> { movie ->
        if (movie != null) {
            recyclerView = activity!!.findViewById(R.id.rv_list_movie)
            rv_list_movie.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = RVMovieAdapter(movie)
            recyclerView.adapter = adapter

            adapter.setOnItemClickCallback(object : RVMovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ResultListMovie) {
                    val moveWithDataIntent = Intent(context, DetailActivity::class.java)
                    moveWithDataIntent.putExtra(DetailActivity.EXTRA_MOVIE, data)
                    moveWithDataIntent.putExtra(DetailActivity.TYPE, DetailActivity.MOVIE)
                    startActivity(moveWithDataIntent)
                }

            })

            pb_movie_fragment.visibility = View.GONE
        }
    }
}
