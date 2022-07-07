/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-06-29
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package com.example.springbatch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 6. 29
 * @version 1.0
*/

@Data
@Entity
public class Address {

	@Id
	@GeneratedValue
	private Long Id;
	private String localtion;

	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
}

