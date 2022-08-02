package com.battleship.models;

import com.battleship.enums.BoardState;
import com.battleship.enums.ShipState;
import com.battleship.utils.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Board {

    private final int[][] grid;

    private final ArrayList<Ship> ships;

    public Board() {
        grid = new int[Constant.BOARD_SIZE][Constant.BOARD_SIZE];
        ships = new ArrayList<>();
    }

    public void putShip(Ship ship) {
        Position head = ship.getHead();

        if (ship.isVertical()) {
            IntStream.range(head.y(), head.y() + ship.getSize())
                    .forEach(i -> {
                        ship.createUnit(new Position(head.x(), i));
                        grid[i][head.x()] = BoardState.BOAT.getValue();
                    });
        } else {
            IntStream.range(head.x(), head.x() + ship.getSize())
                    .forEach(i -> {
                        ship.createUnit(new Position(i, head.y()));
                        grid[head.y()][i] = BoardState.BOAT.getValue();
                    });
        }
        ships.add(ship);
    }

    public ShipState hit(Position pos) {
        Ship ship = null;
        boolean isHit = false;

        for (int i = 0; i < ships.size() && !isHit; i++) {
            ship = ships.get(i);
            isHit = ship.isUnitHit(pos);
        }
        if (isHit) {
            grid[pos.y()][pos.x()] = ship.isAlive()
                    ? ShipState.HIT.getValue()
                    : ShipState.SINK.getValue();
            return ship.isAlive()
                    ? ShipState.HIT
                    : ShipState.SINK;
        }
        grid[pos.y()][pos.x()] = ShipState.MISS.getValue();
        return ShipState.MISS;
    }

    public boolean isValidPlacement(Ship ship) {
        if (!ship.isValidPos()) {
            return false;
        }
        Position head = ship.getHead();

        return ship.isVertical()
                ? IntStream.range(head.y(), head.y() + ship.getSize())
                    .allMatch(i -> grid[i][head.x()] == BoardState.SEA.getValue())
                : IntStream.range(head.x(), head.x() + ship.getSize())
                .allMatch(i -> grid[head.y()][i] == BoardState.SEA.getValue());
    }

    public boolean wasNeverFireAt(Position pos) {
        return grid[pos.y()][pos.x()] != ShipState.HIT.getValue()
                && grid[pos.y()][pos.x()] != ShipState.MISS.getValue()
                && grid[pos.y()][pos.x()] != ShipState.SINK.getValue();
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void displayBoard() {
        displayLetters();

        for (int i = 0; i < grid.length; i++) {

            displayNumbers(i);

            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == BoardState.BOAT.getValue()) {
                    System.out.print("S ");
                } else if (grid[i][j] == ShipState.HIT.getValue()) {
                    System.out.print("X ");
                } else if (grid[i][j] == ShipState.SINK.getValue()) {
                    System.out.print("D ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }

    public void displayEnemyBoard() {
        displayLetters();

        for (int i = 0; i < grid.length; i++) {

            displayNumbers(i);

            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == ShipState.MISS.getValue()) {
                    System.out.print("M ");
                } else if (grid[i][j] == ShipState.HIT.getValue()) {
                    System.out.print("X ");
                } else if (grid[i][j] == ShipState.SINK.getValue()) {
                        System.out.print("D ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }

    private void displayNumbers(int current) {
        if (current + 1 < 10) {
            System.out.printf(" %d ", current + 1);
        } else {
            System.out.printf("%d ", current + 1);
        }
    }

    private void displayLetters() {
        System.out.print("   ");
        Arrays.stream(Constant.LETTERS)
                .forEach(l -> System.out.printf("%s ", l));
        System.out.println();
    }
}