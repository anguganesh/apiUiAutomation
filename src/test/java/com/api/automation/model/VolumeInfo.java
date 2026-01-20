package com.api.automation.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VolumeInfo {

	String title;
	String subtitle;
	List<String> authors;
	String publisher;
	String publishedDate;
	String description;
	List<IndustryIdentifier> industryIdentifiers;
	ReadingModes readingModes;
	Integer pageCount;
	String printType;
	Integer ratingsCount;
	List<String> categories;
	Integer averageRating;
	String maturityRating;
	Boolean allowAnonLogging;
	String contentVersion;
	PanelizationSummary panelizationSummary;
	ImageLinks imageLinks;
	String language;
	String previewLink;
	String infoLink;
	String canonicalVolumeLink;
	
	
	
}
