package com.bootcamp.stock.domain.utils.pagination;

public class PageRequestUtil {
    public PageRequestUtil(int page, int size) {
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
