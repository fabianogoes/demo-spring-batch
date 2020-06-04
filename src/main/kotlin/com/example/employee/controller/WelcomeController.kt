package com.example.employee.controller

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class WelcomeController(
		private val jobLauncher: JobLauncher,
		private val job: Job
) {

	companion object {
		var parameterId: Long = 1
	}

	@GetMapping
	fun get() = "Welcome Spring Batch"

	@GetMapping("/job")
	fun batchStart() {
		println("parameterId = $parameterId")
		val jobParameters = JobParameters(mapOf("id" to JobParameter(parameterId++)))
		jobLauncher.run(job, jobParameters)
	}

}