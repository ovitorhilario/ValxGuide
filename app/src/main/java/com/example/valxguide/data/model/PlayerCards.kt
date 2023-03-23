package com.example.valxguide.data.model

data class PlayerCards(
    val data: List<PlayerCard>,
    val status: Int
)

data class PlayerCard(
    val assetPath: String,
    val displayIcon: String,
    val displayName: String,
    val isHiddenIfNotOwned: Boolean,
    val largeArt: String,
    val smallArt: String,
    val themeUuid: String,
    val uuid: String,
    val wideArt: String
)