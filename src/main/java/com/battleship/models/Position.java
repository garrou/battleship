package com.battleship.models;

import com.battleship.utils.Constant;

public record Position(int x, int y) {

    public boolean isValid() {
        return x >= 0 && x < Constant.BOARD_SIZE && y >= 0 && y < Constant.BOARD_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Position other)) {
            return false;
        }
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
