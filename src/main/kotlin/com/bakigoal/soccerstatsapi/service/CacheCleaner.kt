package com.bakigoal.soccerstatsapi.service

import com.bakigoal.soccerstatsapi.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CacheCleaner(
    @Autowired val leagueRepository: LeagueRepository,
    @Autowired val teamRepository: TeamRepository,
    @Autowired val scorersRepository: ScorersRepository,
    @Autowired val assistsRepository: AssistsRepository,
    @Autowired val standingsRepository: StandingsRepository
) {

    fun cleanCache() {
        leagueRepository.deleteAll()
        teamRepository.deleteAll()
        scorersRepository.deleteAll()
        assistsRepository.deleteAll()
        standingsRepository.deleteAll()
    }
}