package com.ilakk.develop.connected.cities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.core.IsNull;

import com.ilakk.develop.connected.cities.controller.ConnectedCitiesController;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectedCitiesApplicationTests {

	@Autowired
	private ConnectedCitiesController controller;

	@Test
	public void contextLoads() {
		assertThat(controller, is(IsNull.notNullValue()));
	}

}
