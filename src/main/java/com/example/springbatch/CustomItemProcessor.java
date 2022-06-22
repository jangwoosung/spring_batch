/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-06-22
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import org.springframework.batch.item.ItemProcessor;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 6. 22
 * @version 1.0
*/

public class CustomItemProcessor implements ItemProcessor<Customer, Customer> {

	@Override
	public Customer process(Customer customer) throws Exception {

		customer.setName(customer.getName().toUpperCase());
		return customer;
	}
}
