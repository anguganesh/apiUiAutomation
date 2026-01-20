package com.api.automation.model;

import com.google.common.collect.ComparisonChain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetResponseUserDetails extends BaseScenario implements Comparable<GetResponseUserDetails> {

	transient Integer id;
	String name;
	String email;
	String gender;
	String status;

	@Override
	public int compareTo(GetResponseUserDetails responseFromFile) {
		// TODO Auto-generated method stub
		return ComparisonChain.start()
				.compare(this.id, responseFromFile.id)
				.compare(this.name, responseFromFile.name)
				.compare(this.email, responseFromFile.email)
				.compare(this.gender, responseFromFile.gender)
				.compare(this.status, responseFromFile.status)
				.result();
	}

}
