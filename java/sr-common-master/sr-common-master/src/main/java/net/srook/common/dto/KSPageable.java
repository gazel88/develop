package net.srook.common.dto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class KSPageable {
    private long totalElements;
    private int size;
    private int page;
    private int totalPage;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean isLast;
    private KSPrevious previousOrFirst;
    private KSNext next;

    public KSPageable(final long totalElements, final int size, final int page, final int totalPage,
                      final boolean hasNext, final boolean hasPrevious, final boolean isLast,
                      final KSPrevious previousOrFirst, final KSNext next) {
        this.totalElements = totalElements;
        this.size = size;
        this.page = page;
        this.totalPage = totalPage;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.isLast = isLast;
        this.previousOrFirst = previousOrFirst;
        this.next = next;
    }

    public static <T> KSPageable of(final Page<T> page) {
        final Pageable next = page.getPageable().next();
        final Pageable previousOrFirst = page.getPageable().previousOrFirst();
        return new KSPageable(page.getTotalElements(), page.getSize(), page.getNumber(), page.getTotalPages(),
                page.hasNext(), page.hasPrevious(), page.isLast(),
                new KSPrevious(previousOrFirst.getPageSize(), previousOrFirst.getPageNumber()),
                new KSNext(next.getPageSize(), next.getPageNumber()));
    }

    public static <T> KSPageable of(final List<T> contents, final Pageable pageable, final long totalElements) {
        return KSPageable.of(new PageImpl<>(contents, pageable, totalElements));
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getSize() {
        return size;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public boolean isLast() {
        return isLast;
    }

    public KSPrevious getPreviousOrFirst() {
        return previousOrFirst;
    }

    public KSNext getNext() {
        return next;
    }
}
