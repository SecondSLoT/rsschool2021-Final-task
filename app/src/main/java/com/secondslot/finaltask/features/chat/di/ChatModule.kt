package com.secondslot.finaltask.features.chat.di

import androidx.lifecycle.ViewModelProvider
import com.secondslot.finaltask.features.chat.vm.ChatViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ChatModule {

    @ChatScope
    @Binds
    fun bindChatViewModelFactory(impl: ChatViewModelFactory): ViewModelProvider.Factory
}
