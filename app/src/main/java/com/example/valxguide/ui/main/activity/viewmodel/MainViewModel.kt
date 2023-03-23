package com.example.valxguide.ui.main.activity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valxguide.data.model.*
import com.example.valxguide.data.repository.ValorantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainViewModel(
    private val repository: ValorantRepository,
) : ViewModel() {

    val anyIsLoading = MutableLiveData<Boolean>(false)
    val currentDataState = MutableLiveData<DataState>(DataState.Empty)

    private val _agents = MutableLiveData<Agents>()
    val agents: LiveData<Agents> get() = _agents

    private val _weapons = MutableLiveData<Weapons>()
    val weapons: LiveData<Weapons> get() = _weapons

    private val _ranks = MutableLiveData<Rank>()
    val ranks: LiveData<Rank> get() = _ranks

    private val _cards = MutableLiveData<PlayerCards>()
    val cards: LiveData<PlayerCards> get() = _cards

    private val _sprays = MutableLiveData<Sprays>()
    val sprays: LiveData<Sprays> get() = _sprays

    private val _gunBuddies = MutableLiveData<GunBuddies>()
    val gunBuddies: LiveData<GunBuddies> get() = _gunBuddies

    private val _maps = MutableLiveData<Maps>()
    val maps: LiveData<Maps> get() = _maps

    fun fetchData() {

        try {
            viewModelScope.launch(Dispatchers.IO) {
                val preAgents = repository.getAgents()
                val preWeapons = repository.getWeapons()
                val preRanks = repository.getRanks()
                val preCards = repository.getPlayerCards()
                val preSprays = repository.getSprays()
                val preGunBuddies = repository.getGunBuddies()
                val preMaps = repository.getMaps()

                withContext(Dispatchers.Main) {
                    _agents.value = preAgents.validate()
                    _weapons.value = preWeapons
                    _ranks.value = preRanks.validate()
                    _cards.value = preCards
                    _sprays.value = preSprays
                    _gunBuddies.value = preGunBuddies
                    _maps.value = preMaps

                    currentDataState.value = DataState.Full
                }
            }
        } catch (e: IOException) {
            currentDataState.value = DataState.Error
        }
    }

    private fun Agents.validate(): Agents {
        return this.copy(
            data = this.data.filter { it.isPlayableCharacter }
        )
    }

    private fun Weapons.validate(): Weapons {
        return this.copy()
    }

    private fun Ranks.validate(): Rank {
        return this.data.last().copy(
            tiers = this.data.last().tiers.filter {
                !it.division.contains("INVALID") &&
                !it.division.contains("UNRANKED")
            }
        )
    }

    sealed class DataState {
        object Error : DataState()
        object Empty : DataState()
        object Full : DataState()
    }
}