/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-10
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

/**
 * Class description
 *
 * @author jws
 * @since 2022. 1. 10
 * @version 1.0
*/

@Component
public class CustomTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		System.out.println("Custom Step11 was executexd");

		return RepeatStatus.FINISHED;
	}
}
