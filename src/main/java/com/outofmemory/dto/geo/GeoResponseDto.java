package com.outofmemory.dto.geo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GeoResponseDto {
//    private PlusCodeDto plusCode;
    private List<GeoBaseResult> results;
    private String status;
}
