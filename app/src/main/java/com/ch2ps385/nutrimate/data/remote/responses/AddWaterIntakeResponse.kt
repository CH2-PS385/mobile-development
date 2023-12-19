package com.ch2ps385.nutrimate.data.remote.responses

import com.google.gson.annotations.SerializedName

data class AddWaterIntakeResponse(

    @field:SerializedName("data")
    val data: DataPostWaterIntake,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("tracker")
    val tracker: AddTracker
)

data class AddTracker(

    @field:SerializedName("array")
    val array: List<Int>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class DataPostWaterIntake(

    @field:SerializedName("intakes")
    val intakes: List<Int>,

    @field:SerializedName("sum")
    val sum: Int
)
