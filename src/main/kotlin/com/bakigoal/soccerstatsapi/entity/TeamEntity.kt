package com.bakigoal.soccerstatsapi.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "teams")
data class TeamEntity(
    @Id
    val teamId: Int? = null,
    val info_json: String? = null
)