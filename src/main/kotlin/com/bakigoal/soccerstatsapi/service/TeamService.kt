package com.bakigoal.soccerstatsapi.service

import com.bakigoal.soccerstatsapi.entity.TeamEntity
import com.bakigoal.soccerstatsapi.repository.TeamRepository
import com.bakigoal.soccerstatsapi.service.client.SoccerApiClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TeamService(
    @Autowired val teamRepository: TeamRepository,
    @Autowired val soccerApiClient: SoccerApiClient
) {

    fun findById(teamId: Int): String {
        val team = teamRepository.findByIdOrNull(teamId) ?: refreshSquad(teamId)
        return team.info_json ?: "squad with id=$teamId NOT found!!!"
    }

    private fun refreshSquad(teamId: Int): TeamEntity {
        val json = soccerApiClient.getSquad(teamId)
        return teamRepository.save(TeamEntity(teamId, json))
    }
}