/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-06-22
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 6. 22
 * @version 1.0
*/

public class CustomItemWriter implements ItemStreamWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		items.forEach(item -> System.out.println(item));
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("open");
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("update");
	}

	@Override
	public void close() throws ItemStreamException {
		System.out.println("close");
	}
}
