package com.bakigoal.soccerstatsapi.rest

import com.bakigoal.soccerstatsapi.service.TeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/teams")
class TeamController(@Autowired val teamService: TeamService) {

    @GetMapping("/{teamId}/squad")
    fun getSquad(@PathVariable teamId: Int) = Mono.just(teamService.findById(teamId))
}