/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-03-23
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 3. 23
 * @version 1.0
*/

public class CustomJobParametersIncrementer implements JobParametersIncrementer {

	private static final SimpleDateFormat format =  new SimpleDateFormat("yyyyMMdd-hhmmss");

	@Override
	public JobParameters getNext(JobParameters parameters) {

		String id = format.format(new Date());

		return new JobParametersBuilder().addString("run.id", id).toJobParameters();
	}
}
