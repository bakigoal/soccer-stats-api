package com.bakigoal.soccerstatsapi.repository

import com.bakigoal.soccerstatsapi.entity.TeamEntity
import org.springframework.data.repository.CrudRepository

interface TeamRepository : CrudRepository<TeamEntity, Int> {
}