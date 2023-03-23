package com.example.valxguide.data.model

data class Weapons(
    val status: Int,
    val data: List<Weapon>,
)

data class AdsStats(
    val burstCount: Int,
    val fireRate: Double,
    val firstBulletAccuracy: Double,
    val runSpeedMultiplier: Double,
    val zoomMultiplier: Double
)

data class Chroma(
    val assetPath: String,
    val displayIcon: String,
    val displayName: String,
    val fullRender: String,
    val streamedVideo: String,
    val swatch: String,
    val uuid: String
)

data class DamageRange(
    val bodyDamage: Int,
    val headDamage: Double,
    val legDamage: Double,
    val rangeEndMeters: Int,
    val rangeStartMeters: Int
)

data class Weapon(
    val assetPath: String,
    val category: String,
    val defaultSkinUuid: String,
    val displayIcon: String,
    val displayName: String,
    val killStreamIcon: String,
    val shopData: ShopData?,
    val skins: List<Skin>,
    val uuid: String,
    val weaponStats: WeaponStats?
)

data class GridPosition(
    val column: Int,
    val row: Int
)

data class Level(
    val assetPath: String,
    val displayIcon: String,
    val displayName: String,
    val levelItem: String,
    val streamedVideo: String,
    val uuid: String
)

data class ShopData(
    val assetPath: String,
    val canBeTrashed: Boolean,
    val category: String,
    val categoryText: String,
    val cost: Int,
    val gridPosition: GridPosition,
    val image: Any,
    val newImage: String,
    val newImage2: Any
)

data class Skin(
    val assetPath: String,
    val chromas: List<Chroma>,
    val contentTierUuid: String?,
    val displayIcon: String,
    val displayName: String,
    val levels: List<Level>,
    val themeUuid: String,
    val uuid: String,
    val wallpaper: Any
)

data class WeaponStats(
    val adsStats: AdsStats,
    val airBurstStats: Any,
    val altFireType: String,
    val altShotgunStats: Any,
    val damageRanges: List<DamageRange>,
    val equipTimeSeconds: Double,
    val feature: String,
    val fireMode: Any,
    val fireRate: Double,
    val firstBulletAccuracy: Double,
    val magazineSize: Int,
    val reloadTimeSeconds: Double,
    val runSpeedMultiplier: Double,
    val shotgunPelletCount: Int,
    val wallPenetration: String
)