package com.secondslot.finaltask.features.chat.ui

import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.secondslot.finaltask.R
import com.secondslot.finaltask.customview.CustomFlexBoxLayout
import com.secondslot.finaltask.customview.CustomReactionView
import com.secondslot.finaltask.databinding.ItemMessageBinding
import com.secondslot.finaltask.features.chat.model.MessageItem

class MessageViewHolder(
    private val binding: ItemMessageBinding,
    private val listener: MessageInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(message: MessageItem, myId: Int) {
        binding.messageViewGroup.run {
            message.avatarUrl?.let { setUserPhoto(it) }
            message.senderFullName?.let { setUsername(it) }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setMessageText(Html.fromHtml(message.content, FROM_HTML_MODE_COMPACT).toString())
            } else {
                setMessageText(Html.fromHtml(message.content).toString())
            }
        }

        val customFlexBoxLayout =
            binding.messageViewGroup
                .findViewById<CustomFlexBoxLayout>(R.id.custom_flex_box_layout)
        customFlexBoxLayout.removeViews(0, customFlexBoxLayout.childCount - 1)

        if (message.reactions.isNotEmpty()) {
            customFlexBoxLayout.isGone = false

            val index = customFlexBoxLayout.childCount - 1
            for (reaction in message.reactions) {
                val reactionView = CustomReactionView(itemView.context)
                reactionView.run {
                    emoji = reaction.key.emojiCode
                    counter = reaction.value
                    isSelected = reaction.key.userId == myId

                    setOnClickListener {
                        it as CustomReactionView
                        if (it.isSelected) {
                            listener.removeReaction(message.id, reaction.key.emojiName)
                        } else {
                            listener.addReaction(message.id, reaction.key.emojiName)
                        }
                    }
                }
                customFlexBoxLayout.addView(reactionView, index)
            }
        } else {
            customFlexBoxLayout.isGone = true
        }

        if (message.senderId == myId) {
            binding.messageViewGroup.setMessageBgColor(R.color.own_message_background)
            binding.messageViewGroup.setSelfMessageType(true)
        } else {
            binding.messageViewGroup.setMessageBgColor(R.color.on_background)
            binding.messageViewGroup.setSelfMessageType(false)
        }
    }
}
