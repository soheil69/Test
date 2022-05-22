package com.example.template

import android.content.Context
import com.example.template.di.ApplicationContext
import com.example.template.di.modules.ActivityBuilder
import com.example.template.di.modules.NetworkModule
import com.example.template.di.modules.ServiceBuilder
import com.example.template.di.modules.TemplateModule
import com.example.template.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, TemplateModule::class, NetworkModule::class,
        ViewModelModule::class, ActivityBuilder::class, ServiceBuilder::class
    ]
)
interface TemplateComponent {
    fun inject(application: TemplateApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(@ApplicationContext context: Context): Builder
        fun build(): TemplateComponent
    }
}
