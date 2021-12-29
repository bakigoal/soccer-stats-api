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
        var team = teamRepository.findByIdOrNull(teamId)
        if (team == null) {
            val json = soccerApiClient.getSquad(teamId)
            team = teamRepository.save(TeamEntity(teamId, json))
        }
        return team.info_json ?: "squad with id=$teamId NOT found!!!"
    }
}