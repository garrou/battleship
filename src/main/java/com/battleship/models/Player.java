package com.battleship.models;

import com.battleship.enums.ShipOrientation;
import com.battleship.enums.ShipState;
import com.battleship.enums.ShipType;
import com.battleship.utils.Constant;
import com.battleship.utils.Helper;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Player {

    private static final Scanner sc = new Scanner(System.in);

    private final Board playerBoard;

    private final Board enemyBoard;

    public Player(Board pb, Board eb) {
        playerBoard = pb;
        enemyBoard = eb;
    }

    public boolean isLoose() {
        return playerBoard
                .getShips()
                .stream()
                .noneMatch(Ship::isAlive);
    }

    public void putShipsManually() {
        Arrays.stream(ShipType.values())
                .forEach(this::enterPositionAndOrientation);
    }

    public void putShipsAutomatically() {
        Random random = new Random();

        for (ShipType s : ShipType.values()) {
            Ship ship;

            do {
                ship = new Ship(
                        new Position(
                                random.nextInt(Constant.BOARD_SIZE),
                                random.nextInt(Constant.BOARD_SIZE)
                        ),
                        s.getSize(),
                        ShipOrientation.values()[random.nextInt(2)]
                );
            } while (!putShip(ship));
        }
    }

    public void autoFire() {
        Random random = new Random();
        ShipState shipState = null;

        do {
            Position position = new Position(
                    random.nextInt(0, Constant.BOARD_SIZE),
                    random.nextInt(0, Constant.BOARD_SIZE)
            );
            if (wasNeverFireAt(position)) {
                shipState = fireAt(position);
            }
        } while (shipState == null);
    }

    public void enterFireCoordinate() {
        Position pos;

        do {
            displayEnemyBoard();
            System.out.print("Fire coordinate (ex A5) : ");
            String input = sc.nextLine();
            pos = Helper.getPos(input);

            if (!pos.isValid()) {
                System.out.println("Invalid coordinate");
            } else if (wasNeverFireAt(pos)) {
                ShipState enemyShipState = fireAt(pos);

                if (enemyShipState == ShipState.HIT) {
                    System.out.println("HIT !");
                } else if (enemyShipState == ShipState.SINK) {
                    System.out.println("SINK !!!");
                } else {
                    System.out.println("MISS !");
                }
            } else {
                System.out.printf("You already fire at %s.\n", input);
            }
        } while (!pos.isValid());
    }

    public void displayBoard() {
        playerBoard.displayBoard();
    }

    public void displayEnemyBoard() {
        enemyBoard.displayEnemyBoard();
    }

    private boolean putShip(Ship ship) {
        if (!playerBoard.isValidPlacement(ship)) {
            return false;
        }
        playerBoard.putShip(ship);
        return true;
    }

    private boolean wasNeverFireAt(Position pos) {
        return enemyBoard.wasNeverFireAt(pos);
    }

    private ShipState fireAt(Position pos) {
        return enemyBoard.hit(pos);
    }

    private void enterPositionAndOrientation(ShipType s) {
        boolean ok = false;

        displayBoard();

        while (!ok) {
            System.out.printf("Put %s with size %d\n", s.getName(), s.getSize());
            System.out.print("Enter ship head position and orientation (H/V) (ex: A7 H) : ");

            String input = sc.nextLine();
            Position pos = Helper.getPos(input);
            ShipOrientation orientation = Helper.getOrientation(input);

            ok = putShip(new Ship(pos, s.getSize(), orientation));

            if (!ok) {
                System.out.println("Invalid placement.");
            }
        }
    }
}