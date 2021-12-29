package com.bakigoal.soccerstatsapi.repository

import com.bakigoal.soccerstatsapi.entity.ScorersEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ScorersRepository : CrudRepository<ScorersEntity, UUID> {

    fun findFirstByLeagueIdAndSeason(leagueId: Int, season: String): ScorersEntity?
}