package com.secondslot.finaltask.features.chat.ui

import com.secondslot.finaltask.features.chat.model.MessageItem

interface MessageInteractionListener {

    fun openReactionsSheet(message: MessageItem)

    fun addReaction(messageId: Int, emojiName: String)

    fun removeReaction(messageId: Int, emojiName: String)
}
