package com.digel.movielist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

class HomeRepository {

    fun movieResult() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {HomeDataSource()}
    ).liveData
}