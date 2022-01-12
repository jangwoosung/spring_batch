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
public class ExecutionContextTasklet3 implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("ExecutionContextTasklet3 tasklet3");

		Object name = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name");

		if (name == null) {
			chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("name", "user1");
			throw new RuntimeException("step3 was failed");

		}

		return RepeatStatus.FINISHED;
	}

}
