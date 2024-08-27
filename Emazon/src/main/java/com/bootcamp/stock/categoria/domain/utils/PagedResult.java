package com.bootcamp.stock.categoria.domain.utils;

import java.util.List;

public class PagedResult<T> {
    private final List<T> content;
    private final int page;
    private final int pageSize;
    private final int totalPages;
    private final long totalCount;

    public PagedResult(List<T> content, int page, int pageSize, int totalPages, long totalCount) {
        this.content = content;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalCount() {
        return totalCount;
    }
}