package com.digel.movielist.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.digel.movielist.HomeRepository
import com.digel.movielist.entity.Result

class HomeViewModels : ViewModel(){

    private val repository = HomeRepository()

    fun getMovieList() : LiveData<PagingData<Result>> {
        return repository.movieResult()
    }
}