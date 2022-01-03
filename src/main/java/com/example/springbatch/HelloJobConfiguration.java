/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-03
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;


/**
 * Class description
 *
 * @author jws
 * @since 2022. 1. 3
 * @version 1.0
*/

@RequiredArgsConstructor
@Configuration
public class HelloJobConfiguration {

	private final JobBuilderFactory		jobBuilderFactory;
	private final StepBuilderFactory	stepBuilderFactory;


	@Bean
	public Job helloJob() {
		return jobBuilderFactory.get("hellowJob")
				.start(helloStep1())
				.next(helloStep2())
				.build();
	}


	@Bean
	public Step helloStep2() {
		return stepBuilderFactory.get("helloStep2")
		                         // 기본적을 tasklet은 무한바복
		                         .tasklet(new Tasklet() {
			                         @Override
			                         public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				                         System.out.println("Hello2 Spring Batch!!");
				                         System.out.println("Hello2 Spring Batch!!");
				                         return RepeatStatus.FINISHED;
			                         }
		                         })
		                         .build();
	}


	@Bean
	public Step helloStep1() {
		return stepBuilderFactory.get("helloStep1")
		                         // 기본적을 tasklet은 무한바복
		                         .tasklet(new Tasklet() {
			                         @Override
			                         public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				                         System.out.println("Hello1 Spring Batch!!");
				                         System.out.println("Hello1 Spring Batch!!");
				                         return RepeatStatus.FINISHED;
			                         }
		                         })
		                         .build();
	}
}
