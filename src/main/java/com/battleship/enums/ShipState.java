package com.battleship.enums;

public enum ShipState {

    SINK(-10),

    HIT(1),

    MISS(-1);

    private final int value;

    ShipState(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}
