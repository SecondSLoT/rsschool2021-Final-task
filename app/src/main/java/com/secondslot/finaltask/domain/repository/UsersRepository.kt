package com.secondslot.finaltask.domain.repository

import com.secondslot.finaltask.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getUsers(): Flow<List<User>>

    fun getProfileInfo(userId: Int): Flow<List<User>>

    fun getOwnProfile(): Flow<List<User>>
}
