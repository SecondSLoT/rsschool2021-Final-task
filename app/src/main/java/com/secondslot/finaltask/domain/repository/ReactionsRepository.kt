package com.secondslot.finaltask.domain.repository

import com.secondslot.finaltask.data.api.model.SendResult
import com.secondslot.finaltask.data.local.model.ReactionLocal

interface ReactionsRepository {

    suspend fun addReaction(messageId: Int, emojiName: String): SendResult

    suspend fun removeReaction(messageId: Int, emojiName: String): SendResult

    fun getReactions(): List<ReactionLocal>
}
