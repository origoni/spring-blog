package com.millky.blog.application.utility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDateTime date) {
		log.debug("date = {}", date);
		Instant instant = Instant.from(date.atZone(ZoneId.systemDefault()));
		return Date.from(instant);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Date value) {
		log.debug("value", value);
		Instant instant = value.toInstant();
		return LocalDateTime.from(instant.atZone(ZoneId.systemDefault()));
	}
}
