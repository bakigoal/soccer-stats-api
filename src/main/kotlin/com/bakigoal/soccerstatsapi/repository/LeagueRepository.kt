package com.bakigoal.soccerstatsapi.repository

import com.bakigoal.soccerstatsapi.entity.LeagueEntity
import org.springframework.data.repository.CrudRepository

interface LeagueRepository : CrudRepository<LeagueEntity, Int> {
}