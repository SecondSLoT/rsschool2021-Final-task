package com.secondslot.finaltask.data.api.model.response

import com.secondslot.finaltask.data.api.model.UserRemote
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserResponse(
    @field:Json(name = "result") val result: String,
    @field:Json(name = "user") val user: UserRemote
)
