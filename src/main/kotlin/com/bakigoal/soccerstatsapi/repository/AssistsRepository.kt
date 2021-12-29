package com.bakigoal.soccerstatsapi.repository

import com.bakigoal.soccerstatsapi.entity.AssistsEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AssistsRepository : CrudRepository<AssistsEntity, UUID> {

    fun findFirstByLeagueIdAndSeason(leagueId: Int, season: String): AssistsEntity?
}