package com.digel.movielist.network

import android.app.Application
import android.content.Context


class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
        fun getApplicationContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}