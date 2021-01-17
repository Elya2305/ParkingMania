package com.outofmemory.service;

import com.outofmemory.dto.LocationDto;

public interface GeoService {
    String address(LocationDto location);
}
