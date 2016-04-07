package com.validation;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

public class Person {

	@NotNull
	private String name;
	
	@CPF
	private String document;

	public Person(String name, String document) {		
		this.name = name;
		this.document = document;
	}

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}
}
