package com.digel.movielist

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.digel.movielist.viewmodel.HomeViewModels
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeAdapter by lazy {
        HomeAdapter()
    }
    private val viewModel: HomeViewModels by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_home.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = homeAdapter.withLoadStateFooter(footer = HomeStateAdapter {
                    homeAdapter.retry()
                })
        }
        btn_retry.setOnClickListener {
            homeAdapter.retry()
        }
        setupMovieList()
    }

    private fun setupMovieList() {
        homeAdapter.addLoadStateListener {
            progress_bar.isVisible = it.source.refresh is LoadState.Loading
            rv_home.isVisible = it.source.refresh is LoadState.NotLoading
            btn_retry.isVisible = it.source.refresh is LoadState.Error
            txt_error.isVisible = it.source.refresh is LoadState.Error

            if (it.source.refresh is LoadState.NotLoading && it.append.endOfPaginationReached && homeAdapter.itemCount < 1) {
                rv_home.isVisible = false
                txt_error.isVisible = true
            } else {
                txt_error.isVisible = false
            }
        }
        viewModel.getMovieList().observe(this) { result ->
            Log.i("get movie activity", "setupMovieList: result is -> ${result.map { it.id }}")
            homeAdapter.submitData(this.lifecycle, result)
        }
    }
}