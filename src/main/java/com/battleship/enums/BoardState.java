package com.battleship.enums;

public enum BoardState {

    SEA(0),

    BOAT(2);

    private final int value;

    BoardState(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}