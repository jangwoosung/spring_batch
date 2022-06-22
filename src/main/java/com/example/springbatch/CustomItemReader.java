/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-06-22
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 6. 22
 * @version 1.0
*/

public class CustomItemReader implements ItemReader<Customer> {

	private List<Customer> list;

	public CustomItemReader(List<Customer> list) {
		this.list = new ArrayList<>(list);
	}

	@Override
	public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (!list.isEmpty()) {
			return list.remove(0);
		}

		return null;
	}

}
