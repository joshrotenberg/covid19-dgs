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
        @InputArgument("countyFilter") countyFilter: String?, @InputArgument("stateFilter") stateFilter: String?,
        @InputArgument("fipsFilter") fipsFilter: String?
    ): List<County> {
        return countyServiceImpl.filterCounties(countyFilter, stateFilter, fipsFilter)
    }
}