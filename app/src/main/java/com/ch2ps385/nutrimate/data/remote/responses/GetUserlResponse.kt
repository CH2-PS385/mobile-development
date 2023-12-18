package com.ch2ps385.nutrimate.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GetUserlResponse(

	@field:SerializedName("data")
	val data: DataGetUser,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class DataGetUser(

	@field:SerializedName("allergies")
	val allergies: List<String>,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("weight")
	val weight: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("age")
	val age: Int,

	@field:SerializedName("height")
	val height: Int
)
