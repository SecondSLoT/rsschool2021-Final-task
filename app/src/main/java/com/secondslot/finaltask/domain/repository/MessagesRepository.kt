package com.secondslot.finaltask.domain.repository

import com.secondslot.finaltask.data.api.model.SendResult
import com.secondslot.finaltask.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {

    fun getMessages(
        anchor: String,
        numBefore: String,
        numAfter: String,
        narrow: Map<String, Any>
    ): Flow<List<Message>>

    suspend fun sendMessage(
        type: String,
        streamId: Int,
        topicName: String,
        messageText: String
    ): SendResult
}
