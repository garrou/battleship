package com.battleship.models;

import com.battleship.enums.ShipOrientation;
import com.battleship.utils.Constant;

import java.util.ArrayList;

public class Ship {

    private final ArrayList<ShipUnit> units;

    private final Position head;

    private final ShipOrientation orientation;

    private final int size;

    public Ship(Position h, int s, ShipOrientation o) {
        units = new ArrayList<>();
        head = h;
        orientation = o;
        size = s;
    }

    public boolean isUnitHit(Position pos) {
        ShipUnit unit = units
                .stream()
                .filter(u -> u.getPos().equals(pos))
                .findFirst()
                .orElse(null);

        if (unit != null) {
            unit.setIsAlive(false);
            return true;
        }
        return false;
    }

    public void createUnit(Position pos) {
        units.add(new ShipUnit(pos));
    }

    public Position getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public boolean isVertical() {
        return orientation == ShipOrientation.VERTICAL;
    }

    public boolean isValidPos() {
        return head.isValid()
                && ((!isVertical() && head.x() + size - 1 < Constant.BOARD_SIZE)
                    || (isVertical() && head.y() + size - 1 < Constant.BOARD_SIZE));
    }

    public boolean isAlive() {
        return units
                .stream()
                .anyMatch(ShipUnit::getIsAlive);
    }
}
