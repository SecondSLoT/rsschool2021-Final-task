package com.secondslot.finaltask.di

import android.content.Context
import com.secondslot.finaltask.data.repository.di.RepositoryModule
import com.secondslot.finaltask.domain.repository.MessagesRepository
import com.secondslot.finaltask.domain.repository.ReactionsRepository
import com.secondslot.finaltask.domain.repository.StreamsRepository
import com.secondslot.finaltask.domain.repository.UsersRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    fun provideMessagesRepository(): MessagesRepository

    fun provideReactionsRepository(): ReactionsRepository

    fun provideStreamsRepository(): StreamsRepository

    fun provideUsersRepository(): UsersRepository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context
        ): AppComponent
    }
}
