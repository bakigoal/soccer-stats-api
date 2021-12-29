package com.bakigoal.soccerstatsapi.rest

import com.bakigoal.soccerstatsapi.service.LeagueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/leagues")
class LeagueController(@Autowired val leagueService: LeagueService) {

    @GetMapping("/{leagueId}")
    fun getLeague(@PathVariable leagueId: Int) =
        Mono.just(leagueService.findById(leagueId))

    @GetMapping("/{leagueId}/{year}/standings")
    fun standingsAsync(@PathVariable leagueId: Int, @PathVariable year: String) =
        Mono.just(leagueService.getStandings(leagueId, year))

    @GetMapping("/{leagueId}/{year}/topscorers")
    fun topScorersAsync(@PathVariable leagueId: Int, @PathVariable year: String) =
        Mono.just(leagueService.topScorers(leagueId, year))

    @GetMapping("/{leagueId}/{year}/topassists")
    fun topAssistsAsync(@PathVariable leagueId: Int, @PathVariable year: String) =
        Mono.just(leagueService.topAssists(leagueId, year))

}