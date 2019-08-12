package com.mitocode.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
//clase utilitaria para pasar de LocalDate a Date (y visceversa) que no es necesaria en springboot versión 2
@Converter(autoApply = true)
public class MyLocalDateConverter implements AttributeConverter<java.time.LocalDate, java.sql.Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		return attribute == null ? null : java.sql.Date.valueOf(attribute);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		return dbData == null ? null : dbData.toLocalDate();
	}

}
