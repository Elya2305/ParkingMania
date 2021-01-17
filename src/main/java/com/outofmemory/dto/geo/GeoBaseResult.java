package com.outofmemory.dto.geo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GeoBaseResult {
    private List<String> types;
    private String formattedAddress;
    private List<AddressComponent> addressComponents;
//    private String placeId;
//    private List<String> plusCode;
}
