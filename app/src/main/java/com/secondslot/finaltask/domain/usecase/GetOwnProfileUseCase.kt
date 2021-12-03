package com.secondslot.finaltask.domain.usecase

import com.secondslot.finaltask.domain.model.User
import com.secondslot.finaltask.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOwnProfileUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    fun execute(): Flow<List<User>> {
        return usersRepository.getOwnProfile()
    }
}
