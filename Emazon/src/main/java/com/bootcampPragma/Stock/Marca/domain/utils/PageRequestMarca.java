package com.bootcampPragma.Stock.Marca.domain.utils;

public class PageRequestMarca {
    public PageRequestMarca(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    private final int page;
    private final int size;
}
