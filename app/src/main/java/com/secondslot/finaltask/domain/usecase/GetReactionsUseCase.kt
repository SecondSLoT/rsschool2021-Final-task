package com.secondslot.finaltask.domain.usecase

import com.secondslot.finaltask.data.local.model.ReactionLocal
import com.secondslot.finaltask.domain.repository.ReactionsRepository
import javax.inject.Inject

class GetReactionsUseCase @Inject constructor(
    private val reactionsRepository: ReactionsRepository
) {

    fun execute(): List<ReactionLocal> {
        return reactionsRepository.getReactions()
    }
}
