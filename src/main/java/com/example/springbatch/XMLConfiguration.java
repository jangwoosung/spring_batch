/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-04
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

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
public class XMLConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Customer, Customer>chunk(3)
				.reader(customItemReader())
				.writer(customItemWriter())
				.build();
	}


	@Bean
	public StaxEventItemReader<Customer> customItemReader() {
		return new StaxEventItemReaderBuilder<Customer>().name("xmlFileItemReader")
		                                                 .resource(new ClassPathResource("customer.xml"))
		                                                 .addFragmentRootElements("customer")
		                                                 .unmarshaller(itemMarshaller())
		                                                 .build();
	}

	@Bean
	public XStreamMarshaller itemMarshaller() {
		Map<String, Class<?>> aliases = new HashMap<>();
		aliases.put("customer", Customer.class);
		aliases.put("id", Long.class);
		aliases.put("name", String.class);
		aliases.put("age", Integer.class);
		XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		xStreamMarshaller.setAliases(aliases);
		return xStreamMarshaller;
	}


	@Bean
	public ItemWriter<Customer> customItemWriter() {
        return items -> {
            for (Customer item : items) {
                System.out.println(item.toString());
            }
        };
    }

}
