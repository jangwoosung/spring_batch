/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-10
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 1. 10
 * @version 1.0
*/

@Component
@RequiredArgsConstructor
public class JobParameterTest implements ApplicationRunner {

	private final JobLauncher jobLauncher;
	private final Job job;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		JobParameters jobParamteres = new JobParametersBuilder()
			.addString("name", "user1")
			.addLong("seq", 2L)
			.addDate("date", new Date())
			.addDouble("age", 16.5D)
			.toJobParameters();

		jobLauncher.run(job, jobParamteres);
	}
}
