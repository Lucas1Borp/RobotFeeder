package com.inmetrics.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Document(collection = "sites")
public class Sites {

	@Id
	private String id;
	private String name;
	private String address;

	public Sites(String name, String address) {
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Sites [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}
