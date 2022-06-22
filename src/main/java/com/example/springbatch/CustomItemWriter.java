/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-06-22
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 6. 22
 * @version 1.0
*/

public class CustomItemWriter implements ItemWriter<Customer> {

	@Override
	public void write(List<? extends Customer> items) throws Exception {
		items.forEach(item -> System.out.println(item));
	}
}
