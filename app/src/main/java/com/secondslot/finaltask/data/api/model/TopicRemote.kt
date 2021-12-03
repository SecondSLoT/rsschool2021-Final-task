package com.secondslot.finaltask.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopicRemote(
    @field:Json(name = "name") val topicName: String,
    @field:Json(name = "max_id") val maxMessageId: Int
)
