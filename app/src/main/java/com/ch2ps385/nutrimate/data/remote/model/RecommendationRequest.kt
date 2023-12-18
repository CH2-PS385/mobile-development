package com.ch2ps385.nutrimate.data.remote.model

data class RecommendationRequest(
    val calories: Int,
    val allergies: List<Any>
)
