package com.springboot.eduportal.studentservice.service.impl;

import org.springframework.stereotype.Component;

import com.springboot.eduportal.studentservice.service.Vehicle;

@Component
public class Truck /* implements Vehicle */ {

	// @Override
	public void drive() {
		System.out.println("Driving a Truck");
	}

}
