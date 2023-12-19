package com.ch2ps385.nutrimate.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GetWaterIntakeResponse(

    @field:SerializedName("data")
    val data: DataGetWaterIntake,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("tracker")
    val tracker: GetTracker
)

data class GetTracker(

    @field:SerializedName("array")
    val array: List<Int>,

    @field:SerializedName("isEmpty")
    val isEmpty: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class DataGetWaterIntake(

    @field:SerializedName("intakes")
    val intakes: List<Int>,

    @field:SerializedName("sum")
    val sum: Int
)
