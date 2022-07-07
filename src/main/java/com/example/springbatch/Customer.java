/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-06-29
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import lombok.Data;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 6. 29
 * @version 1.0
*/

@Data
public class Customer {

	private Long id;
	private String firstname;
	private String lastname;
	private String birthdate;

}

