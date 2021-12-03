package com.secondslot.finaltask.domain.usecase

import com.secondslot.finaltask.data.api.model.SendResult
import com.secondslot.finaltask.domain.repository.MessagesRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository
) {

    suspend fun execute(
        type: String = "stream",
        streamId: Int,
        topicName: String,
        messageText: String
    ): SendResult {
        return messagesRepository.sendMessage(type, streamId, topicName, messageText)
    }
}
