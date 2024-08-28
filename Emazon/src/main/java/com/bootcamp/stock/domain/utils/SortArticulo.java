package com.bootcamp.stock.domain.utils;

public class SortArticulo {
    public String getProperty() {
        return property;
    }

    public Direction getDirection() {
        return direction;
    }

    private final String property;

    public SortArticulo(String property, Direction direction) {
        this.property = property;
        this.direction = direction;
    }

    private final Direction direction;

    public enum Direction {
        ASC, DESC
    }
}

