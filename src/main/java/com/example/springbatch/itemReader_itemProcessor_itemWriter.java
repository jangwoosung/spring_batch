/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-04
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import java.util.Arrays;

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
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
public class itemReader_itemProcessor_itemWriter {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job childJob() {
		return jobBuilderFactory.get("childJob")
				.start(step1())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Customer, Customer>chunk(3)
				.reader(itemReader())
				.processor(itemProcessor())
				.writer(itemWriter())
				.build();
	}

	@Bean
	public ItemWriter<Customer>itemWriter() {
		return new CustomItemWriter();
	}

	@Bean
	public ItemProcessor<? super Customer, ? extends Customer> itemProcessor() {
		return new CustomItemProcessor();
	}

	@Bean
	public ItemReader<Customer> itemReader() {
		return new CustomItemReader(Arrays.asList(new Customer("user1"), new Customer("user2"), new Customer("user3")));
	}


	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("step2 was executed");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

}
