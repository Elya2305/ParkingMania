package com.outofmemory.service.impl;

import com.outofmemory.dto.LocationDto;
import com.outofmemory.dto.geo.GeoBaseResult;
import com.outofmemory.dto.geo.GeoResponseDto;
import com.outofmemory.exception.ValidationException;
import com.outofmemory.service.GeoService;
import com.outofmemory.utils.client.GeocodingClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class GeoServiceImpl implements GeoService {
    private final GeocodingClient geocodingClient;
    private static final String STREET_ADDRESS = "street_address";
    private static final String DEFAULT_ADDRESS = "Longitude: %s, Latitude %s";

    @Override
    public String address(LocationDto location) {
        validate(location);
        GeoResponseDto geo = geocodingClient.decode(location);
        return geo.getResults().stream().filter(o -> o.getTypes().contains(STREET_ADDRESS))
                .map(GeoBaseResult::getFormattedAddress).findFirst()
                .orElse(String.format(DEFAULT_ADDRESS, location.getLat(), location.getLng()));
    }

    private void validate(LocationDto location) {
        if (isNull(location) || isNull(location.getLat()) || isNull(location.getLng())) {
            throw new ValidationException("Please provide coordinates");
        }
    }
}
