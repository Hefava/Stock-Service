package com.bootcamp.stock.domain.utils;

public class SortCategory {
    public String getProperty() {
        return property;
    }

    public Direction getDirection() {
        return direction;
    }

    private final String property;

    public SortCategory(String property, Direction direction) {
        this.property = property;
        this.direction = direction;
    }

    private final Direction direction;

    public enum Direction {
        ASC, DESC
    }
}

