package com.joshrotenberg.covid19.repositories

import java.time.LocalDate
import javax.persistence.*

@Entity
data class County(
    var date: LocalDate,
    var county: String,
    var state: String,
    var fips: String,
    var cases: Long,
    var deaths: Long,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)
