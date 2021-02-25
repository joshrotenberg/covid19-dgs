package com.joshrotenberg.covid19.services

import com.example.demo.generated.types.County
import com.joshrotenberg.covid19.Database
import org.springframework.stereotype.Service

@Service
class CountyService(val database: Database) {

    fun countyDataForCountyName(county: String): String {
        return "some county"
    }

    fun filterCounties(countyFilter: String?, stateFilter: String?, fipsFilter: String?): List<County> {
        return database.countyQueries().withFilters(county = countyFilter, state = stateFilter, fips = null).executeAsList().map {
            County(
                date = it.date,
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
                date = it.date,
                county = it.county,
                state = it.state,
                fips = it.fips,
                cases = it.cases?.toInt(),
                deaths = it.deaths?.toInt()
            )
        }
    }
}