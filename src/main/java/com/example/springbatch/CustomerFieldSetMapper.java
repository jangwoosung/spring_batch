/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-06-29
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 6. 29
 * @version 1.0
*/

public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {

	@Override
	public Customer mapFieldSet(FieldSet fieldSet) throws BindException {

		if (fieldSet == null) {
			return null;
		}

		Customer customer = new Customer();
//		customer.setName(fieldSet.readString(0));
//		customer.setAge(fieldSet.readInt(1));
//		customer.setYear(fieldSet.readString(3));

		customer.setName(fieldSet.readString("name"));
		customer.setAge(fieldSet.readInt("age"));
		customer.setYear(fieldSet.readString("year"));

		return customer;
	}
}
