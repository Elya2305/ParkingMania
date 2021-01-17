package com.outofmemory.utils.client;

import com.outofmemory.dto.LocationDto;
import com.outofmemory.dto.geo.GeoResponseDto;

public interface GeocodingClient {
    GeoResponseDto decode(double lng, double lat);

    GeoResponseDto decode(LocationDto location);
}
