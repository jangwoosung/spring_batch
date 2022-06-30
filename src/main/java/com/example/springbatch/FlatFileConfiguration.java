/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-04
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
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

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
public class FlatFileConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job() {
		return jobBuilderFactory.get("batchJob")
				.start(step1())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<String, String>chunk(5)
				.reader(itemReader())
				.writer(null)
				.build();
	}

	@Bean
	public ItemReader itemReader() {

		FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new ClassPathResource("/customer.csv"));

		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper(new CustomerFieldSetMapper());

		itemReader.setLineMapper(lineMapper);
		// 첫번째 라인 무시
		itemReader.setLinesToSkip(1);

		return itemReader;
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
