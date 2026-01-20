package com.api.automation.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Items {

	String kind;
	String id;
	String etag;
	String selfLink;
	VolumeInfo volumeInfo;
	SaleInfo saleInfo;
	AccessInfo accessInfo;
	SearchInfo searchInfo;
	
}
