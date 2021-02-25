package com.joshrotenberg.covid19.batch.fieldsetmapper

import com.joshrotenberg.covid19.data.County
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FieldSet
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class CountyFieldSetMapper : FieldSetMapper<County> {
    override fun mapFieldSet(fieldSet: FieldSet): County {
        return County(
            fieldSet.readString("date"),
//            toLocalDate(fieldSet.readDate("date", "yyyy-MM-dd").toString()),
            fieldSet.readString("county"),
            fieldSet.readString("state"),
            fieldSet.readString("fips"),
            fieldSet.readLong("cases", 0),
            fieldSet.readLong("deaths", 0)
        )
    }

    private fun toLocalDate(date: Date): LocalDate {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }
}
