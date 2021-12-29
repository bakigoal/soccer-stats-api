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
        var league = leagueRepository.findByIdOrNull(leagueId)
        if (league == null) {
            val json = soccerApiClient.getLeague(leagueId)
            league = leagueRepository.save(LeagueEntity(leagueId, json))
        }
        return league.info_json ?: "league with id=$leagueId NOT found!!!"
    }

    fun getStandings(leagueId: Int, year: String): String {
        var standings = standingsRepository.findFirstByLeagueIdAndSeason(leagueId, year)
        if (standings == null) {
            val json  = soccerApiClient.standingsAsync(leagueId, year)
            standings = standingsRepository.save(StandingsEntity(UUID.randomUUID(), leagueId, year, json))
        }
        return standings.info_json ?: "standings with leagueId=$leagueId and year=$year NOT found!!!"
    }

    fun topScorers(leagueId: Int, year: String): String {
        var scorers = scorersRepository.findFirstByLeagueIdAndSeason(leagueId, year)
        if (scorers == null) {
            val json  = soccerApiClient.topScorersAsync(leagueId, year)
            scorers = scorersRepository.save(ScorersEntity(UUID.randomUUID(), leagueId, year, json))
        }
        return scorers.info_json ?: "scorers with leagueId=$leagueId and year=$year NOT found!!!"
    }

    fun topAssists(leagueId: Int, year: String): String {
        var assists = assistsRepository.findFirstByLeagueIdAndSeason(leagueId, year)
        if (assists == null) {
            val json  = soccerApiClient.topAssistsAsync(leagueId, year)
            assists = assistsRepository.save(AssistsEntity(UUID.randomUUID(), leagueId, year, json))
        }
        return assists.info_json ?: "assists with leagueId=$leagueId and year=$year NOT found!!!"
    }
}