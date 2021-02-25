package com.joshrotenberg.covid19

import com.joshrotenberg.covid19.data.CountyQueries
import com.joshrotenberg.db.Covid19
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class Database {

    private lateinit var driver: JdbcSqliteDriver

    @PostConstruct
    fun init() {
        driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Covid19.Schema.create(driver)
    }

    fun countyQueries(): CountyQueries {
        return Covid19(driver).countyQueries
    }
}