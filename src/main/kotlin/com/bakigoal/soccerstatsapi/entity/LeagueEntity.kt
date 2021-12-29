package com.bakigoal.soccerstatsapi.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "leagues")
data class LeagueEntity(
    @Id
    val id: Int? = null,
    val info_json: String? = null
)