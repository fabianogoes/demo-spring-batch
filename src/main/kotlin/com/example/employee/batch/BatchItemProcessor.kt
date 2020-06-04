package com.example.employee.batch

import com.example.employee.model.Employee
import com.example.employee.model.Profile
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor;
import java.util.*

class BatchItemProcessor : ItemProcessor<Employee?, Profile?> {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun process(employee: Employee): Profile {
        logger.info("process(${employee})")
        val empCode = employee.id?.toLong()
        return Profile(
                id = UUID.randomUUID(),
                empCode = empCode!!,
                empName = employee.firstName!!,
                profileName = employee.lastName!!
        ).also { logger.info("Profile{ empCode=${it.empCode}, empName=${it.empName}, profileName=${it.profileName} }") }
    }

}