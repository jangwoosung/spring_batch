/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-12
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 1. 12
 * @version 1.0
*/

@Slf4j
@Component
public class ExecutionContextTasklet1 implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("ExecutionContextTasklet1 tasklet1");

		// job 아래 포함된 step간 공유 가능
		ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
		// step간 공유 불가
		ExecutionContext stepExecutionContext =  contribution.getStepExecution().getExecutionContext();

		String jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
		String stepName = chunkContext.getStepContext().getStepExecution().getStepName();

		if (jobExecutionContext.get("jobName") == null) {
			jobExecutionContext.put("jobName", jobName);
		}

		if (stepExecutionContext.get("stepName") == null) {
			stepExecutionContext.put("stepName", stepName);
		}

		log.info("jobName : %s", jobExecutionContext.get("jobName"));
		log.info("stepName :" + stepExecutionContext.get("stepName"));

		return RepeatStatus.FINISHED;
	}

}
