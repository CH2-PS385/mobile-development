package com.ch2ps385.nutrimate.data.remote.responses

import com.google.gson.annotations.SerializedName

data class AddUserByEmailResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)
