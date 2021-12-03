package com.secondslot.finaltask.domain.usecase

import com.secondslot.finaltask.data.api.model.SendResult
import com.secondslot.finaltask.domain.repository.ReactionsRepository
import javax.inject.Inject

class AddReactionUseCase @Inject constructor(
    private val reactionsRepository: ReactionsRepository
) {

    suspend fun execute(
        messageId: Int,
        emojiName: String
    ): SendResult {
        return reactionsRepository.addReaction(messageId, emojiName)
    }
}
