package com.bakigoal.soccerstatsapi.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "assists")
data class AssistsEntity(
    @Id
    val id: UUID? = null,
    val leagueId: Int? = null,
    val season: String? = null,
    val info_json: String? = null
)