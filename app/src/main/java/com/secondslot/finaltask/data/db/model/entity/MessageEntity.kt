package com.secondslot.finaltask.data.db.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.secondslot.finaltask.domain.model.Message

@Entity(tableName = "messages")
class MessageEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "sender_id") val senderId: Int,
    @ColumnInfo(name = "sender_full_name") val senderFullName: String?,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "topic_name") val topicName: String?, // Foreign key
    @ColumnInfo(name = "timestamp") val timestamp: Int,
    @ColumnInfo(name = "isMeMessage") val isMeMessage: Boolean
) {

    companion object {
        fun fromMessage(message: Message): MessageEntity = MessageEntity(
            id = message.id,
            senderId = message.senderId,
            senderFullName = message.senderFullName,
            avatarUrl = message.avatarUrl,
            content = message.content,
            topicName = message.topicName,
            timestamp = message.timestamp,
            isMeMessage = message.isMeMessage
        )
    }
}
