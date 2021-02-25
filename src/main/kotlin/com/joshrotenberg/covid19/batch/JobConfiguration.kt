package com.joshrotenberg.covid19.batch

import com.joshrotenberg.covid19.Database
import com.joshrotenberg.covid19.batch.fieldsetmapper.CountyFieldSetMapper
import com.joshrotenberg.covid19.data.County
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.UrlResource
import org.springframework.scheduling.annotation.Scheduled
import java.util.*

@Configuration
@EnableBatchProcessing
class JobConfiguration(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
    val jobLauncher: JobLauncher,
    val db: Database
) {

    @Value("\${covid19.county.url}")
    lateinit var  countyUrl: String

    @Bean
    fun countyItemReader(): FlatFileItemReader<County> {
        val reader = FlatFileItemReader<County>()
        reader.setLinesToSkip(1)
        reader.setMaxItemCount(1000)
        reader.setResource(UrlResource(countyUrl))

        val countyLineMapper = DefaultLineMapper<County>()
        val tokenizer = DelimitedLineTokenizer()
        tokenizer.setNames("date", "county", "state", "fips", "cases", "deaths")

        countyLineMapper.setLineTokenizer(tokenizer)
        countyLineMapper.setFieldSetMapper(CountyFieldSetMapper())
        countyLineMapper.afterPropertiesSet()

        reader.setLineMapper(countyLineMapper)

        return reader
    }

    @Bean
    fun countyItemWriter(): ItemWriter<County> {
        return ItemWriter<County> { items ->
            items.forEach { item ->
                db.countyQueries()
                    .insert(item.date!!, item.county, item.state, item.fips, item.cases, item.deaths)
            }
        }
    }

    @Bean
    fun step1(): Step {
        return stepBuilderFactory.get("step1")
            .chunk<County, County>(1000)
            .reader(countyItemReader())
            .writer(countyItemWriter())
            .allowStartIfComplete(true)
            .build()
    }

    @Bean
    fun job(): Job {
        return jobBuilderFactory.get("job")
            .start(step1())
            .build()
    }

    @Scheduled(fixedRate = 50000L)
    fun runJob() {
        val params = JobParametersBuilder().addDate("jobTime", Date()).toJobParameters()
        val execution = jobLauncher.run(job(), params)
        println(db.countyQueries().selectAll().executeAsList().count())
    }
}