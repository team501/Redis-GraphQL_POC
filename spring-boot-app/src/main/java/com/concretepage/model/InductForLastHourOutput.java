package com.concretepage.model;

import java.util.List;

import lombok.Data;

@Data
public class InductForLastHourOutput {

	List<USSStatistics> lstUssStatistics;
	Integer duration;
	String condition;
	long totalInducts;
	Integer measure;
	
}
