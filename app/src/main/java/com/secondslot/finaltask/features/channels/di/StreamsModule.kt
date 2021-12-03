package com.secondslot.finaltask.features.channels.di

import androidx.lifecycle.ViewModelProvider
import com.secondslot.finaltask.features.channels.vm.StreamsListViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface StreamsModule {

    @StreamsScope
    @Binds
    fun bindStreamsListViewModelFactory(
        impl: StreamsListViewModelFactory
    ): ViewModelProvider.Factory
}
