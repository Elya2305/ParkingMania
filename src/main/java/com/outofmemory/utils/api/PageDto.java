package com.outofmemory.utils.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PageDto<T> {
    private final T objectList;
    private final Integer total;
    private final Integer page;
}
