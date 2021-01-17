package com.outofmemory.utils.client.impl;

import com.outofmemory.dto.LocationDto;
import com.outofmemory.dto.geo.GeoResponseDto;
import com.outofmemory.utils.AbstractHttpClient;
import com.outofmemory.utils.client.GeocodingClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingClientImpl extends AbstractHttpClient implements GeocodingClient {
    @Value("${google.maps.api.key}")
    private String apiKey;
    private static final String DECODE_URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=[lng],[lat]&key=[YOUR_API_KEY]";

    public GeocodingClientImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public GeoResponseDto decode(double lng, double lat) {
        return get(url(lng, lat), GeoResponseDto.class);
    }

    @Override
    public GeoResponseDto decode(LocationDto location) {
        return decode(location.getLng(), location.getLat());
    }

    private String url(double lng, double lat) {
        return DECODE_URL.replace("[YOUR_API_KEY]", apiKey)
                .replace("[lng]", String.valueOf(lng))
                .replace("[lat]", String.valueOf(lat));
    }

    @Override
    public HttpHeaders headers() {
        return new HttpHeaders();
    }
}
