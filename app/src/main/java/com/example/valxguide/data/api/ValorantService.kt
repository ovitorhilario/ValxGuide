package com.example.valxguide.data.api

import com.example.valxguide.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ValorantService {

    @GET("agents")
    suspend fun getAgents(@Query("language") lang: String = "en-US") : Agents

    @GET("weapons")
    suspend fun getWeapons(@Query("language") lang: String = "en-US") : Weapons

    @GET("competitivetiers")
    suspend fun getRanks(@Query("language") lang: String = "en-US") : Ranks

    @GET("playercards")
    suspend fun getPlayerCards(@Query("language") lang: String = "en-US") : PlayerCards

    @GET("buddies")
    suspend fun getGunBuddies(@Query("language") lang: String = "en-US") : GunBuddies

    @GET("maps")
    suspend fun getMaps(@Query("language") lang: String = "en-US") : Maps

    @GET("sprays")
    suspend fun getSprays(@Query("language") lang: String = "en-US") : Sprays

}