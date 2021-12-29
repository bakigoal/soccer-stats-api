package com.bakigoal.soccerstatsapi.tasks

import com.bakigoal.soccerstatsapi.service.LeagueService
import com.bakigoal.soccerstatsapi.service.TeamService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class UpdateLeagueTask(@Autowired val leagueService: LeagueService, @Autowired val teamService: TeamService) {

    @Scheduled(fixedRate = 12 * 60 * 60 * 1000)
    fun reportCurrentTime() {
        logger.info("start refreshing data (every 12 hour)...")
        leagueService.refreshAll()
        teamService.refreshSquads()
        logger.info("end refreshing data ...")
    }
}