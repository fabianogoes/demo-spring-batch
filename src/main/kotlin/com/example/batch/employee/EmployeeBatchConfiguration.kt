package com.example.batch.employee

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableBatchProcessing
class EmployeeBatchConfiguration(
        private val jobBuilderFactory: JobBuilderFactory,
        private val stepBuilderFactory: StepBuilderFactory
) {

    companion object {
        const val CHUNK_SIZE = 5
        const val JOB_NAME = "readEmployeeCSVFilesJob"
        const val STEP_NAME = "step1"
    }

    @Bean
    fun readCSVFilesJob(): Job = jobBuilderFactory
            .get(JOB_NAME)
            .incrementer(RunIdIncrementer())
            .start(step1())
            .build()

    @Bean
    fun step1(): Step = stepBuilderFactory[STEP_NAME]
            .chunk<Employee, Employee>(CHUNK_SIZE)
            .reader(reader())
            .writer(writer())
            .build()

    @Bean
    fun reader(): FlatFileItemReader<Employee> {
        return EmployeeBatchCSVReader()
    }

    @Bean
    fun writer(): EmployeeBatchWriter {
        return EmployeeBatchWriter()
    }
}

