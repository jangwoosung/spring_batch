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
import org.springframework.batch.item.ItemStreamReader;
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

public class CustomeItemStreamReader implements ItemStreamReader<String> {

	private final List<String> items;
	private int index = -1;
	private boolean restart = false;

	public CustomeItemStreamReader(List<String> items) {
		this.items = items;
		this.index = 0;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		String item = null;

		if (this.index < this.items.size()) {
			item = this.items.get(index);
			index++;
		}

		if (this.index == 6 && !restart) {
			throw new RuntimeException("Restart is requried");

		}

		return item;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {

		// index라는 key가 존재시
		if (executionContext.containsKey("index")) {
			index = executionContext.getInt("index");
			this.restart = true;
		} else {
			index = 0;
			executionContext.put("index", index);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {

		executionContext.put("index", index);
	}

	@Override
	public void close() throws ItemStreamException {

		System.out.println("close");
	}
}
