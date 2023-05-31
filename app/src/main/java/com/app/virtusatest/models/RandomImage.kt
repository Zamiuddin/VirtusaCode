package com.app.virtusatest.models

import com.google.gson.annotations.SerializedName

data class RandomImage(
    @SerializedName("message") var message: String? = null,
    @SerializedName("status") var status: String? = null
)