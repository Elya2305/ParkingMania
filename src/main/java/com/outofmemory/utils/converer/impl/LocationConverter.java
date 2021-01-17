package com.outofmemory.utils.converer.impl;

import com.outofmemory.dto.LocationDto;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.StringJoiner;

@Converter
public class LocationConverter implements AttributeConverter<LocationDto, String> {
    private static final String DELIMITER = " : ";

    @Override
    public String convertToDatabaseColumn(LocationDto locationDto) {
        return new StringJoiner(DELIMITER).add(String.valueOf(locationDto.getLng()))
                .add(String.valueOf(locationDto.getLat())).toString();
    }

    @Override
    public LocationDto convertToEntityAttribute(String location) {
        String[] coordinates = location.split(DELIMITER);
        LocationDto result = new LocationDto();
        result.setLng(Double.parseDouble(coordinates[0]));
        result.setLat(Double.parseDouble(coordinates[1]));
        return result;
    }
}
