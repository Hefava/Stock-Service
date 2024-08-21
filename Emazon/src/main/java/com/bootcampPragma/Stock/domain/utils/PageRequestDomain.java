package com.bootcampPragma.Stock.domain.utils;

public class PageRequestDomain {
    public PageRequestDomain(int page, int size) {
        this.page = page;
        this.size = size;
    }

    private final int page;

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    private final int size;
}
