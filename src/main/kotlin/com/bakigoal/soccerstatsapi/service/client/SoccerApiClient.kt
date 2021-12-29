package com.bakigoal.soccerstatsapi.service.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "soccer-api", url = "\${services.soccer-api.url}")
interface SoccerApiClient {

    @GetMapping(
        "leagues",
        headers = ["x-rapidapi-host=\${services.soccer-api.x-rapidapi-host}", "x-rapidapi-key=\${services.soccer-api.x-rapidapi-key}"]
    )
    fun getLeague(@RequestParam("id") id: Int): String


    @GetMapping(
        "standings",
        headers = ["x-rapidapi-host=\${services.soccer-api.x-rapidapi-host}", "x-rapidapi-key=\${services.soccer-api.x-rapidapi-key}"]
    )
    fun standingsAsync(@RequestParam("league") league: Int, @RequestParam("season") year: String): String

    @GetMapping(
        "players/topscorers",
        headers = ["x-rapidapi-host=\${services.soccer-api.x-rapidapi-host}", "x-rapidapi-key=\${services.soccer-api.x-rapidapi-key}"]
    )
    fun topScorersAsync(@RequestParam("league") league: Int, @RequestParam("season") year: String): String

    @GetMapping(
        "players/topassists",
        headers = ["x-rapidapi-host=\${services.soccer-api.x-rapidapi-host}", "x-rapidapi-key=\${services.soccer-api.x-rapidapi-key}"]
    )
    fun topAssistsAsync(@RequestParam("league") league: Int, @RequestParam("season") year: String): String

    @GetMapping(
        "players/squads",
        headers = ["x-rapidapi-host=\${services.soccer-api.x-rapidapi-host}", "x-rapidapi-key=\${services.soccer-api.x-rapidapi-key}"]
    )
    fun getSquad(@RequestParam("team") teamId: Int): String
}