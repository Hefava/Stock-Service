package com.bootcamp.stock.domain.utils;

public class SortMarca {
    public SortMarca(String property, Direction direction) {
        this.property = property;
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public Direction getDirection() {
        return direction;
    }

    private final String property;
    private final Direction direction;

    public enum Direction {
        ASC, DESC
    }
}
