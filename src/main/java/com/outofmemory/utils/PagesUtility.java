package com.outofmemory.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PagesUtility {
    public static Pageable createPageableUnsorted(Integer page, Integer pageSize) {
        return PageRequest.of(Objects.nonNull(page) ? page : 0, Objects.nonNull(pageSize) ? pageSize : 10);
    }

    public static Pageable createSortPageRequest(Integer page, Integer pageSize, SortOrder asc, String... fields) {
        return PageRequest.of(Objects.nonNull(page) ? page : 0, Objects.nonNull(pageSize) ? pageSize : 10, asc == SortOrder.ASC ? Sort.by(fields).ascending() : Sort.by(fields).descending());
    }

    public enum SortOrder {
        ASC, DESC
    }
}
