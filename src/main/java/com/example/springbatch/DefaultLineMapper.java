/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-06-29
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 6. 29
 * @version 1.0
*/

public class DefaultLineMapper<T> implements LineMapper<T>{

	private LineTokenizer tokenizer;
	private FieldSetMapper<T> fieldSetMapper;

	@Override
	public T mapLine(String line, int lineNumber) throws Exception {
		return fieldSetMapper.mapFieldSet(tokenizer.tokenize(line));
	}

	public void setLineTokenizer(LineTokenizer lineTokenizer) {
		this.tokenizer = lineTokenizer;
	}

	public void setFieldSetMapper(FieldSetMapper<T> fieldSetMapper) {
		this.fieldSetMapper = fieldSetMapper;
	}
}
