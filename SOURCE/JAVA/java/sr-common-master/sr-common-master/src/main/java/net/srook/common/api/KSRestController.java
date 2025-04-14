package net.srook.common.api;

import static org.springframework.data.domain.PageRequest.of;

import org.springframework.data.domain.Pageable;

public interface KSRestController {
    int MIN_SIZE = 1;
    int MAX_SIZE = 1000;
    int DEFAULT_SIZE = 25;
    int DEFAULT_PAGE = 0;

    default Pageable toPageable(final int page, final int size) {
        return of(page, size);
    }

    default int convertValidSize(final int size) {
        if (size > MAX_SIZE) {
            return MAX_SIZE;
        }
        if (size < MIN_SIZE) {
            return DEFAULT_SIZE;
        }
        return size;
    }
}
