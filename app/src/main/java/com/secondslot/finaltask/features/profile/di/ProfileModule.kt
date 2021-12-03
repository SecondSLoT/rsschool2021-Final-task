package com.secondslot.finaltask.features.profile.di

import androidx.lifecycle.ViewModelProvider
import com.secondslot.finaltask.features.profile.vm.ProfileViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ProfileModule {

    @ProfileScope
    @Binds
    fun bindProfileViewModelFactory(
        impl: ProfileViewModelFactory
    ): ViewModelProvider.Factory
}
