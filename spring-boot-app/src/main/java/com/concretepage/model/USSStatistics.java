package com.concretepage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class USSStatistics {

	private Integer id; 
	private Integer ussItemId; 
	private Integer unitSorterSide; 
	private Integer ussInducttd;
	private Integer wcsInducttd;
	private Integer ussRouteRequesttd; 
	private String wcsRouteRequesttd; 
	private String waveName;
	private Integer wcsOrderId; 
	private Integer itemId; 
	private String expectedChuteName;
	private String exceptionTypeName;
	private String divertedChuteName;
	private Integer ussDiverttd;
	private String wcsDiverttd;
	private Integer inventoryId; 
	private Integer wcsOrderFillRequestId; 
	private Integer ussDivertconfirmtd;
	private String wcsDivertConfirmtd;
	private String created;
	private String updated; 
	private String inductionLane;
	private Integer scancount;
	
}