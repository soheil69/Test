package com.example.template.di.modules

import com.example.template.di.FragmentScope
import com.example.template.fragments.detail.DetailFragment
import com.example.template.fragments.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal class MainActivityModule

@Module
internal interface MainFragmentBuilder {

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class, HomeChildFragmentBuilder::class])
    @FragmentScope
    fun homeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [DetailFragmentModule::class, DetailChildFragmentBuilder::class])
    @FragmentScope
    fun detailFragment(): DetailFragment
}
