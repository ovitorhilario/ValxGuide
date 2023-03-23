package com.example.valxguide.data.repository

import com.example.valxguide.data.model.*

interface ValorantRepository  {
    suspend fun getAgents() : Agents
    suspend fun getWeapons() : Weapons
    suspend fun getRanks() : Ranks
    suspend fun getPlayerCards() : PlayerCards
    suspend fun getMaps() : Maps
    suspend fun getSprays() : Sprays
    suspend fun getGunBuddies() : GunBuddies
}