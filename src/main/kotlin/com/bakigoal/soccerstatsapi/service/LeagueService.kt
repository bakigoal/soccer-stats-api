package com.bakigoal.soccerstatsapi.service

import com.bakigoal.soccerstatsapi.entity.AssistsEntity
import com.bakigoal.soccerstatsapi.entity.LeagueEntity
import com.bakigoal.soccerstatsapi.entity.ScorersEntity
import com.bakigoal.soccerstatsapi.entity.StandingsEntity
import com.bakigoal.soccerstatsapi.repository.AssistsRepository
import com.bakigoal.soccerstatsapi.repository.LeagueRepository
import com.bakigoal.soccerstatsapi.repository.ScorersRepository
import com.bakigoal.soccerstatsapi.repository.StandingsRepository
import com.bakigoal.soccerstatsapi.service.client.SoccerApiClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class LeagueService(
    @Autowired val leagueRepository: LeagueRepository,
    @Autowired val standingsRepository: StandingsRepository,
    @Autowired val scorersRepository: ScorersRepository,
    @Autowired val assistsRepository: AssistsRepository,
    @Autowired val soccerApiClient: SoccerApiClient
) {

    fun findById(leagueId: Int): String {
        val league = leagueRepository.findByIdOrNull(leagueId) ?: refreshLeague(leagueId)
        return league.info_json ?: "league with id=$leagueId NOT found!!!"
    }

    fun getStandings(leagueId: Int, year: String): String {
        val standings =
            standingsRepository.findFirstByLeagueIdAndSeason(leagueId, year) ?: refreshStandings(leagueId, year)
        return standings.info_json ?: "standings with leagueId=$leagueId and year=$year NOT found!!!"
    }

    fun topScorers(leagueId: Int, year: String): String {
        val scorers = scorersRepository.findFirstByLeagueIdAndSeason(leagueId, year) ?: refreshScorers(leagueId, year)
        return scorers.info_json ?: "scorers with leagueId=$leagueId and year=$year NOT found!!!"
    }

    fun topAssists(leagueId: Int, year: String): String {
        val assists = assistsRepository.findFirstByLeagueIdAndSeason(leagueId, year) ?: refreshAssists(leagueId, year)
        return assists.info_json ?: "assists with leagueId=$leagueId and year=$year NOT found!!!"
    }

    fun refreshLeague(leagueId: Int): LeagueEntity {
        val json = soccerApiClient.getLeague(leagueId)
        return leagueRepository.save(LeagueEntity(leagueId, json))
    }

    fun refreshStandings(leagueId: Int, year: String): StandingsEntity {
        val json = soccerApiClient.standingsAsync(leagueId, year)
        return standingsRepository.save(StandingsEntity(UUID.randomUUID(), leagueId, year, json))
    }

    fun refreshScorers(leagueId: Int, year: String): ScorersEntity {
        val json = soccerApiClient.topScorersAsync(leagueId, year)
        return scorersRepository.save(ScorersEntity(UUID.randomUUID(), leagueId, year, json))
    }

    fun refreshAssists(leagueId: Int, year: String): AssistsEntity {
        val json = soccerApiClient.topAssistsAsync(leagueId, year)
        return assistsRepository.save(AssistsEntity(UUID.randomUUID(), leagueId, year, json))
    }
}