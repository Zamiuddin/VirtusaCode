package com.app.virtusatest.models

import com.google.gson.annotations.SerializedName

data class BreadsList(
    @SerializedName("message") var message: ArrayList<String> = arrayListOf(),
    @SerializedName("status") var status: String? = null
)