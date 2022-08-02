package com.battleship.models;

public class ShipUnit {

    private final Position pos;

    private boolean isAlive;

    public ShipUnit(Position p) {
        isAlive = true;
        pos = p;
    }

    public Position getPos() {
        return pos;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean value) {
        isAlive = value;
    }
}
