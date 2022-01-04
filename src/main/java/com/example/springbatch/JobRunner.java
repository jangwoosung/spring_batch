/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-04
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

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
 * @since 2022. 1. 4
 * @version 1.0
*/

@RequiredArgsConstructor
@Component
public class JobRunner implements ApplicationRunner {

	private final JobLauncher jobLauncher; // 스프링배치가 초기화될때 이미 bean 생성 되어 있음
	private final Job job; // 우리가 만든 Job을 의존성받아옴

	@Override
	public void run(ApplicationArguments args) throws Exception {

		JobParameters jobParameters = new JobParametersBuilder()
				.addString("name", "user2")
				.toJobParameters();

		jobLauncher.run(job, jobParameters);
	}

}
