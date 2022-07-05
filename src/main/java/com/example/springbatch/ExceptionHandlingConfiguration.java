/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-04
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

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
public class ExceptionHandlingConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job")
				.start(step1())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<String, String>chunk(5)
				.reader(itemReader())
				.writer(new ItemWriter() {
					@Override
					public void write(List items) throws Exception {
						System.out.println("items = " + items);
					}
				})
				.build();
	}

	// custom api(DelimetedLineTokenLizer)
//	@Bean
//	public ItemReader itemReader() {
//
//		FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
//		itemReader.setResource(new ClassPathResource("/customer.csv"));
//
//		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
//		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
//		lineMapper.setFieldSetMapper(new CustomerFieldSetMapper());
//
//		itemReader.setLineMapper(lineMapper);
//		// 첫번째 라인 무시
//		itemReader.setLinesToSkip(1);
//
//		return itemReader;
//	}

	// spring에서 제공하는 api(DelimetedLineTokenLizer)
//	@Bean
//	public ItemReader itemReader() {
//		return new FlatFileItemReaderBuilder<Customer>()
//				.name("flatFile")
//				.resource(new ClassPathResource("/customer.csv"))
////				.fieldSetMapper(new CustomerFieldSetMapper())
//				.fieldSetMapper(new BeanWrapperFieldSetMapper<>())
//				.targetType(Customer.class)
//				.linesToSkip(1)
//				.delimited().delimiter(",")
//				.names("name", "age", "year")
//				.build();
//	}

	@Bean
	public FlatFileItemReader itemReader() {
		return new FlatFileItemReaderBuilder<Customer>()
				.name("flatFile")
				.resource(new FileSystemResource("D:\\spring_batch\\src\\main\\resources\\customer.txt"))
				.fieldSetMapper(new BeanWrapperFieldSetMapper<>())
				.targetType(Customer.class)
				.linesToSkip(1)
				.strict(false) // 엄격한 룰적용(기본값 true)
				.fixedLength()
				.addColumns(new Range(1,4))
				.addColumns(new Range(5,8))
				.addColumns(new Range(9,10))
				.names("name", "age", "year")
				.build();
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
