package com.digel.movielist

import android.util.Log
import androidx.paging.PagingSource
import com.digel.movielist.entity.Result
import com.digel.movielist.network.Network

class HomeDataSource : PagingSource<Int,Result>() {

    private val page = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: page
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = currentPage + 1
            val responseMovie = Network.getRoutes().getMovie(page = currentPage)
            val result = responseMovie.results
            LoadResult.Page(result,prevPage,nextPage)
        }catch (e: Throwable){
            Log.i("paging error", "Error : -> ${e.printStackTrace()}")
            LoadResult.Error(e)
        }
    }
}