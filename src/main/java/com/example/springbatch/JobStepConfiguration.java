/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-04
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.core.step.job.JobParametersExtractor;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.RuntimeCryptoException;

import lombok.RequiredArgsConstructor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 1. 4
 * @version 1.0
*/

@Configuration
@RequiredArgsConstructor
public class JobStepConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job parentJob() {
		return jobBuilderFactory.get("parentJob")
				.incrementer(new RunIdIncrementer())
				.start(jobStep(null))
				.next(step2())
				.build();
	}

	@Bean
	public Step jobStep(JobLauncher jobLauncher) {
		return stepBuilderFactory.get("jobStep")
				.job(childJob()) // JobStep 내에서 실행 될 Job 설정, JobStepBuilder 반환
				.launcher(jobLauncher) // Job을 실행할 JobLauncher
				.parametersExtractor(jobParametersExtractor()) // Step의 ExecutiuonContext를 Job이 실행되는데 필요한 JobParameters로 변환
				.listener(new StepExecutionListener() {
					@Override
					public void beforeStep(StepExecution stepExecution) {
						stepExecution.getExecutionContext().putString("name", "user1");
					}


					@Override
					public ExitStatus afterStep(StepExecution stepExecution) {
						return null;
					}
				})

				.build();
	}

	private DefaultJobParametersExtractor jobParametersExtractor() {
		DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
		extractor.setKeys(new String[] {"name"});
		return extractor;
	}

	@Bean
	public Job childJob() {
		return jobBuilderFactory.get("childJob")
				.start(step1())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println("step1 was executed");
						return RepeatStatus.FINISHED;
					}
				})
				.build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
					.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println("step2 was executed");
						return RepeatStatus.FINISHED;
					}
				})
				.build();
	}

}
