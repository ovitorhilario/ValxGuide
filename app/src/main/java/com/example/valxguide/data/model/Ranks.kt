package com.example.valxguide.data.model

data class Ranks(
    val status: Int,
    val data: List<Rank>
)

data class Rank(
    val assetObjectName: String,
    val assetPath: String,
    val tiers: List<Tier>,
    val uuid: String
)

data class Tier(
    val backgroundColor: String,
    val color: String,
    var division: String,
    val divisionName: String,
    val largeIcon: String,
    val rankTriangleDownIcon: String,
    val rankTriangleUpIcon: String,
    val smallIcon: String,
    val tier: Int,
    val tierName: String
)