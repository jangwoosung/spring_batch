/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-03-25
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 3. 25
 * @version 1.0
*/

public class CustomTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		String stepName = contribution.getStepExecution().getStepName();
		String jobName = chunkContext.getStepContext().getJobName();


		System.out.println("stemName :" + stepName);
		System.out.println("jobName : "  + jobName);

		return RepeatStatus.FINISHED;
	}

}
