package com.example.employee.batch

import com.example.employee.repository.ProfileRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.stereotype.Component

@Component
class JobExecutionListener(private val profileRepository: ProfileRepository): JobExecutionListener {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun beforeJob(jobExecution: JobExecution) {
        logger.info("Executing job id: ${jobExecution.id} ")
    }

    override fun afterJob(jobExecution: JobExecution) {
        if (jobExecution.status === BatchStatus.COMPLETED) {
            logger.info("Number of Records: ${profileRepository.count()}")
        }
    }

}