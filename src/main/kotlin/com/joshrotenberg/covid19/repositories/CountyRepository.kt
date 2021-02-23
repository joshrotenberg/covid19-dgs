package com.joshrotenberg.covid19.repositories

import org.springframework.data.repository.CrudRepository

interface CountyRepository : CrudRepository<County, Long> {
}