package br.com.helpthenext.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
 
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
  
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
    	if(localDateTime != null) {
    		return Timestamp.valueOf(localDateTime);
    	}
    	return null; 
    }
 
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
    	if(timestamp != null) {
    		return timestamp.toLocalDateTime();
    	}
    	return null;
    }
    
}