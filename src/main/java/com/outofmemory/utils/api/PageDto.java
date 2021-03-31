package com.outofmemory.utils.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class PageDto<T> {
    private final List<T> objectList;
    private final Integer total;
    private final Integer page;
}
