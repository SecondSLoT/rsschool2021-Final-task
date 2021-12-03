package com.secondslot.finaltask.data.api.model.response

import com.secondslot.finaltask.data.api.model.MessageRemote
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MessagesResponse(
    @field:Json(name = "result") val result: String,
    @field:Json(name = "messages")val messages: List<MessageRemote>
)
