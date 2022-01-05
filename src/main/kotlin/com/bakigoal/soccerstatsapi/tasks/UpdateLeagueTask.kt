package com.bakigoal.soccerstatsapi.tasks

import com.bakigoal.soccerstatsapi.service.CacheCleaner
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class UpdateLeagueTask(@Autowired val cacheCleaner: CacheCleaner) {

    @Scheduled(fixedRate = 12 * 60 * 60 * 1000)
    fun reportCurrentTime() {
        logger.info("start refreshing data ...")
        cacheCleaner.cleanCache()
        logger.info("end refreshing data ...")
    }
}