package com.bootcampPragma.Stock.Categoria.domain.utils;

public class SortDomain {
    public String getProperty() {
        return property;
    }

    public Direction getDirection() {
        return direction;
    }

    private final String property;

    public SortDomain(String property, Direction direction) {
        this.property = property;
        this.direction = direction;
    }

    private final Direction direction;

    public enum Direction {
        ASC, DESC
    }
}

