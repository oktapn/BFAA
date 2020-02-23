package com.okta.moviecatalogue.ui.tvshow


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
import com.okta.moviecatalogue.model.tvshowlist.ResultListTvShow
import com.okta.moviecatalogue.networking.Service
import com.okta.moviecatalogue.ui.adapter.RVTvShowAdapter
import com.okta.moviecatalogue.ui.detail.DetailActivity
import com.okta.moviecatalogue.utils.BaseAppFragment
import com.okta.moviecatalogue.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tvshow.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : BaseAppFragment() {
    private lateinit var adapter: RVTvShowAdapter
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var recyclerView: RecyclerView
    @Inject
    lateinit var service: Service

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deps.inject(this)
        tvShowViewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)
        tvShowViewModel.tvShow.observe(this,getTvshow)
        tvShowViewModel.setTvshows(BuildConfig.TSDB_API_KEY,"en-US", service)

        pb_tv_fragment.visibility = View.VISIBLE
    }

    private val getTvshow = Observer<List<ResultListTvShow>> { tvshow ->
        if (tvshow != null) {
            recyclerView = activity!!.findViewById(R.id.rv_list_tv)
            rv_list_tv.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = RVTvShowAdapter(tvshow)
            recyclerView.adapter = adapter

            adapter.setOnItemClickCallback(object : RVTvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ResultListTvShow) {
                    val moveWithDataIntent = Intent(context, DetailActivity::class.java)
                    moveWithDataIntent.putExtra(DetailActivity.EXTRA_MOVIE, data)
                    moveWithDataIntent.putExtra(DetailActivity.TYPE, DetailActivity.TV)
                    startActivity(moveWithDataIntent)
                }

            })

            pb_tv_fragment.visibility = View.GONE
        }
    }
}
