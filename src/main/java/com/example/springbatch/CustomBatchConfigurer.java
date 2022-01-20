/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-01-20
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;


import javax.sql.DataSource;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 1. 20
 * @version 1.0
*/

@Configuration

public class CustomBatchConfigurer extends BasicBatchConfigurer {

	private final DataSource dataSource;

	/**
	 * @param properties
	 * @param dataSource
	 * @param transactionManagerCustomizers
	 */
	protected CustomBatchConfigurer(BatchProperties properties, DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers) {
		super(properties, dataSource, transactionManagerCustomizers);
		this.dataSource = dataSource;
	}

	@Override
	protected JobRepository createJobRepository() throws Exception {

		JobRepositoryFactoryBean factory =  new JobRepositoryFactoryBean();
		factory.setDataSource(dataSource);
		factory.setTransactionManager(getTransactionManager());
		factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
		factory.setTablePrefix("SYSTEM_");

		return factory.getObject();
	}

}
