package com.secondslot.finaltask.features.chat.ui

import com.secondslot.finaltask.data.local.model.ReactionLocal

interface ChooseReactionListener {

    fun reactionChosen(reaction: ReactionLocal)
}
