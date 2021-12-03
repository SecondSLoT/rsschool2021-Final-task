package com.secondslot.finaltask.features.chat.model

import com.secondslot.finaltask.mapper.BaseMapper
import com.secondslot.finaltask.domain.model.Message
import com.secondslot.finaltask.domain.model.Reaction

data class MessageItem(
    val id: Int,
    val senderId: Int,
    val senderFullName: String?,
    val avatarUrl: String?,
    val content: String,
    val topic: String?,
    val timestamp: Int,
    val isMeMessage: Boolean,
    var reactions: Map<Reaction, Int>
) : ChatItem

object MessageToItemMapper : BaseMapper<List<Message>, List<MessageItem>> {

    override fun map(type: List<Message>?): List<MessageItem> {
        return type?.map {
            MessageItem(
                id = it.id,
                senderId = it.senderId,
                senderFullName = it.senderFullName,
                avatarUrl = it.avatarUrl,
                content = it.content,
                topic = it.topicName,
                timestamp = it.timestamp,
                isMeMessage = it.isMeMessage,
                reactions = it.reactions.groupingBy { reaction -> reaction }.eachCount()
            )
        } ?: emptyList()
    }
}
