package com.secondslot.finaltask.features.profile.vm

import androidx.lifecycle.ViewModel
import com.secondslot.finaltask.domain.model.User
import com.secondslot.finaltask.domain.usecase.GetOwnProfileUseCase
import com.secondslot.finaltask.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.flow.Flow

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val getOwnProfileUseCase: GetOwnProfileUseCase
) : ViewModel() {

    fun loadProfile(userId: Int): Flow<List<User>> =
        if (userId != -1) {
            getProfileUseCase.execute(userId)
        } else {
            getOwnProfileUseCase.execute()
        }
}
