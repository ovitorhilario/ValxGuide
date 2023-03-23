package com.example.valxguide.data.repository

import com.example.valxguide.data.api.ValorantService
import com.example.valxguide.data.model.*

class ValorantRepositoryImpl (
    private val valorantService: ValorantService,
) : ValorantRepository {

    override suspend fun getAgents() : Agents = valorantService.getAgents()

    override suspend fun getWeapons(): Weapons = valorantService.getWeapons()

    override suspend fun getRanks(): Ranks = valorantService.getRanks()

    override suspend fun getPlayerCards(): PlayerCards = valorantService.getPlayerCards()

    override suspend fun getMaps(): Maps = valorantService.getMaps()

    override suspend fun getSprays(): Sprays = valorantService.getSprays()

    override suspend fun getGunBuddies(): GunBuddies = valorantService.getGunBuddies()
}
