package com.example.employee.batch

import com.example.employee.model.Employee
import com.example.employee.model.Profile
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
@EnableBatchProcessing
class BatchConfiguration(
        private val jobBuilderFactory: JobBuilderFactory,
        private val stepBuilderFactory: StepBuilderFactory
) {

    companion object {
        const val CHUNK_SIZE = 5
        const val JOB_NAME = "readEmployeeCSVFilesJob"
        const val STEP_NAME = "step1"
    }

    @Bean
    fun readCSVFilesJob(listener: JobExecutionListener, step1: Step): Job =
            jobBuilderFactory
                    .get(JOB_NAME)
                    .incrementer(RunIdIncrementer())
                    .listener(listener)
                    .flow(step1)
                    .end()
                    .build()

    @Bean
    fun step1(reader: ItemReader<Employee>, writer: ItemWriter<Profile>, processor: ItemProcessor<Employee, Profile>) =
            stepBuilderFactory[STEP_NAME]
                    .chunk<Employee, Profile>(CHUNK_SIZE)
                    .reader(reader)
                    .processor(processor)
                    .writer(writer)
                    .build()

    @Bean
    fun reader(): FlatFileItemReader<Employee> {
        return BatchCSVReader()
    }

    @Bean
    fun writer(dataSource: DataSource): JdbcBatchItemWriter<Profile> {
        return BatchDatabaseWriter(dataSource)
    }

    @Bean
    fun processor(): ItemProcessor<Employee?, Profile?>? {
        return BatchItemProcessor()
    }

    @Bean
    fun jdbcTemplate(dataSource: DataSource?): JdbcTemplate? {
        return JdbcTemplate(dataSource!!)
    }
}

