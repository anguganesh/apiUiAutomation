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
public class AccessInfo {

	String country;
	String viewability;
	Boolean embeddable;
	Boolean publicDomain;
	String textToSpeechPermission;
	Epub epub;
	Pdf pdf;
	String webReaderLink;
	String accessViewStatus;
	Boolean quoteSharingAllowed;
	
}
