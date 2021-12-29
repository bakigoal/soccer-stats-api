package com.bakigoal.soccerstatsapi.repository

import com.bakigoal.soccerstatsapi.entity.StandingsEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface StandingsRepository : CrudRepository<StandingsEntity, UUID> {

    fun findFirstByLeagueIdAndSeason(leagueId: Int, season: String): StandingsEntity?
}