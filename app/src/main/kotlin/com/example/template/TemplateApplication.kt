package com.example.template

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TemplateApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerTemplateComponent.builder()
            .applicationContext(applicationContext)
            .build().inject(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}
