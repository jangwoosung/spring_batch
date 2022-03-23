/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-03-23
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 3. 23
 * @version 1.0
*/

public class CustomJobParametersValidator implements JobParametersValidator {

	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {

		if (parameters.getString("name") == null) {
			throw new JobParametersInvalidException("name parameters is not found");
		}
	}
}
