/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-20
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;


import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 1. 20
 * @version 1.0
*/


@Component
@RequiredArgsConstructor
public class JobRepositroyListener implements JobExecutionListener {

	private final JobRepository jobRepositroy;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		String jobName = jobExecution.getJobInstance().getJobName();
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("requestDate", "20210102").toJobParameters();


		JobExecution lastJobExecution = jobRepositroy.getLastJobExecution(jobName, jobParameters);
		if (lastJobExecution != null) {
			for (StepExecution execution : lastJobExecution.getStepExecutions()) {
				BatchStatus status = execution.getStatus();
				System.out.println("status : " + status);
				ExitStatus exitStatus = execution.getExitStatus();
				System.out.println("exitStatus : " + exitStatus);
				String stepName =  execution.getStepName();
				System.out.println("stepName : " + stepName);
			}
		}

	}

}
