package com.example.valxguide.data.model

data class Sprays(
    val `data`: List<Spray>,
    val status: Int
)

data class LevelSpray(
    val assetPath: String,
    val displayIcon: String,
    val displayName: String,
    val sprayLevel: Int,
    val uuid: String
)

data class Spray(
    val animationGif: String,
    val animationPng: String,
    val assetPath: String,
    val category: String,
    val displayIcon: String,
    val displayName: String,
    val fullIcon: String,
    val fullTransparentIcon: String,
    val levels: List<LevelSpray>,
    val themeUuid: String,
    val uuid: String
)