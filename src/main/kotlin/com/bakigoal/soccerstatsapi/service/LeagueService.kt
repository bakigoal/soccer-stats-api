package com.bakigoal.soccerstatsapi.service

import com.bakigoal.soccerstatsapi.entity.LeagueEntity
import com.bakigoal.soccerstatsapi.repository.LeagueRepository
import com.bakigoal.soccerstatsapi.service.client.SoccerApiClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LeagueService(
    @Autowired val leagueRepository: LeagueRepository,
    @Autowired val soccerApiClient: SoccerApiClient
) {

    fun findById(leagueId: Int): String {
        val league = leagueRepository.findByIdOrNull(leagueId) ?: return fetchFromApi(leagueId)
        return league.info_json ?: "league with id=$leagueId NOT found!!!"
    }

    private fun fetchFromApi(leagueId: Int): String {
        val json = soccerApiClient.getLeague(leagueId)
        val leagueInCache = leagueRepository.save(LeagueEntity(leagueId, json))
        return leagueInCache.info_json ?: "league with id=$leagueId NOT found!!!"
    }
}