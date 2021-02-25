package com.joshrotenberg.covid19

import com.joshrotenberg.covid19.services.CountyService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest(classes = [CountyService::class, Database::class])
class CountyServiceTests @Autowired constructor(val countyService: CountyService, val db: Database) {

    @Test
    fun `i test stuff`() {
        assertThat(countyService.countyDataForCountyName("what")).isEqualTo("some county")
        db.countyQueries().insert(LocalDate.of(2021, 1, 1).toString(), "Alameda", "California", "12345", 1, 2)
        db.countyQueries().insert(LocalDate.of(2021, 1, 1).toString(), "Alameda", "California", "12345", 2, 5)
        println(db.countyQueries().selectAll().executeAsList())
//        assertThat(Database.iOk()).isEqualTo("ok")
//        assertThat(Database.doof).isEqualTo("i am doof")
    }

}