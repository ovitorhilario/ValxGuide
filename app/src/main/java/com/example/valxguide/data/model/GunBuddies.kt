package com.example.valxguide.data.model

data class GunBuddies(
    val `data`: List<Buddie>,
    val status: Int
)

data class LevelBuddie(
    val assetPath: String,
    val charmLevel: Int,
    val displayIcon: String,
    val displayName: String,
    val uuid: String
)

data class Buddie(
    val assetPath: String,
    val displayIcon: String,
    val displayName: String,
    val isHiddenIfNotOwned: Boolean,
    val levels: List<LevelBuddie>,
    val themeUuid: String,
    val uuid: String
)