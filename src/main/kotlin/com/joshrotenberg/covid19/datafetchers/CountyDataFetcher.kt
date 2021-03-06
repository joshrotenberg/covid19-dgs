package com.joshrotenberg.covid19.datafetchers

import com.example.demo.generated.DgsConstants
import com.example.demo.generated.types.County
import com.joshrotenberg.covid19.services.CountyService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class CountyDataFetcher(private val countyServiceImpl: CountyService) {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Counties)
    fun counties(
        @InputArgument("county") county: String?,
        @InputArgument("state") state: String?,
        @InputArgument("fips") fips: String?,
        @InputArgument("on") on: String?,
        @InputArgument("before") before: String?,
        @InputArgument("after") after: String?,
        @InputArgument("casesGTE") casesGTE: Long?,
        @InputArgument("casesLTE") casesLTE: Long?,
        @InputArgument("deathsGTE") deathsGTE: Long?,
        @InputArgument("deathsLTE") deathsLTE: Long?,
    ): List<County> {
        return countyServiceImpl.filterCounties(county, state, fips, on, before, after, casesGTE, casesLTE, deathsGTE, deathsLTE)
    }
}