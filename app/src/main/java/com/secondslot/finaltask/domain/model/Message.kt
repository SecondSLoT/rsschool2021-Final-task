package com.secondslot.finaltask.domain.model

data class Message(
    val id: Int,
    val senderId: Int,
    val senderFullName: String?,
    val avatarUrl: String?,
    val content: String,
    val topicName: String?,
    val timestamp: Int,
    val isMeMessage: Boolean,
    var reactions: List<Reaction>
)
