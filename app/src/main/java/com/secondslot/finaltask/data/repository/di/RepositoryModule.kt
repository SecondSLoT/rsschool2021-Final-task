package com.secondslot.finaltask.data.repository.di

import com.secondslot.finaltask.data.repository.MessagesRepositoryImpl
import com.secondslot.finaltask.data.repository.ReactionsRepositoryImpl
import com.secondslot.finaltask.data.repository.StreamsRepositoryImpl
import com.secondslot.finaltask.data.repository.UsersRepositoryImpl
import com.secondslot.finaltask.domain.repository.MessagesRepository
import com.secondslot.finaltask.domain.repository.ReactionsRepository
import com.secondslot.finaltask.domain.repository.StreamsRepository
import com.secondslot.finaltask.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindMessageRepository(impl: MessagesRepositoryImpl): MessagesRepository

    @Binds
    fun bindReactionsRepository(impl: ReactionsRepositoryImpl): ReactionsRepository

    @Binds
    fun bindStreamsRepository(impl: StreamsRepositoryImpl): StreamsRepository

    @Binds
    fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository
}
