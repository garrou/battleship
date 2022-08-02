package com.battleship.enums;

public enum ShipType {

    AIRCRAFT_CARRIER("Aircraft Carrier", 5),

    BATTLESHIP("Battleship", 4),

    DESTROYER("Destroyer", 3),

    SUBMARINE("Submarine", 3),

    PATROL("Patrol", 2);

    private final String name;

    private final int size;

    ShipType(String n, int s) {
        name = n;
        size = s;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
