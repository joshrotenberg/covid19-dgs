package com.joshrotenberg.covid19.services

import com.example.demo.generated.types.County
import com.joshrotenberg.covid19.Database
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class CountyService(val database: Database) {

    fun countyDataForCountyName(county: String): String {
        return "some county"
    }

    fun filterCounties(
        county: String?,
        state: String?,
        fips: String?,
        on: String?,
        before: String?,
        after: String?,
        casesGTE: Long?,
        casesLTE: Long?,
        deathsGTE: Long?,
        deathsLTE: Long?,

    ): List<County> {
        return database.countyQueries().withFilters(
            county = county,
            state = state,
            fips = fips,
            on = on,
            before = before,
            after = after,
            casesGTE = casesGTE,
            casesLTE = casesLTE,
            deathsGTE = deathsGTE,
            deathsLTE = deathsLTE,
        )
            .executeAsList().map {
                County(
                    date = LocalDate.parse(it.date, DateTimeFormatter.ISO_DATE),
                    county = it.county,
                    state = it.state,
                    fips = it.fips,
                    cases = it.cases?.toInt(),
                    deaths = it.deaths?.toInt()
                )
            }
    }

    fun counties(): List<County> {
        return database.countyQueries().selectAll().executeAsList().map {
            County(
                date = LocalDate.parse(it.date, DateTimeFormatter.ISO_DATE),
                county = it.county,
                state = it.state,
                fips = it.fips,
                cases = it.cases?.toInt(),
                deaths = it.deaths?.toInt()
            )
        }
    }
}