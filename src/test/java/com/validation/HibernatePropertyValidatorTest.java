package com.validation;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HibernatePropertyValidatorTest {

	private static Validator validator;
	
	private static String CPF_PROPERTY = "document";
	private static String NAME_PROPERTY = "name";
	
	private Person invalidPerson;
	private Person validPerson;
	
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Before
	public void setUpPerson() {
		invalidPerson = new Person(null, "12345678912");
		validPerson = new Person("Fabio", "03077320886");
	}
	
	@Test
	public void shouldValidateOnlyCPFPropertyWithAnInvalidCPF() {
		Set<ConstraintViolation<Person>> violations = validator.validateProperty(invalidPerson, CPF_PROPERTY);
		assertEquals(1, violations.size());
		assertEquals("CPF inválido", violations.iterator().next().getMessage());
	}
	
	@Test
	public void shouldValidateOnlyCPFPropertyWithAValidCPF() {
		Set<ConstraintViolation<Person>> violations = validator.validateProperty(validPerson, CPF_PROPERTY);
		assertEquals(true, violations.isEmpty());
	}
	
	@Test
	public void shouldValidateOnlyNamePropertyWithANullValue() {
		Set<ConstraintViolation<Person>> violations = validator.validateProperty(invalidPerson, NAME_PROPERTY);
		assertEquals(1, violations.size());		
		assertEquals("não pode ser nulo", violations.iterator().next().getMessage());
	}
	
	@Test
	public void shouldValidateAllProperties() {
		Set<ConstraintViolation<Person>> violations = validator.validate(invalidPerson);
		assertEquals(2, violations.size());
	}
}
