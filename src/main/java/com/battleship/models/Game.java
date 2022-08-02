package com.battleship.models;

import com.battleship.enums.ShipOrientation;
import com.battleship.utils.Helper;

public class Game {

    private final Player player;

    private final Player computer;

    public Game(Player pOne, Player pTwo) {
        player = pOne;
        computer = pTwo;
    }

    public void launch() {

        // TODO: ask to put manually or auto
        player.putShipsManually();

        computer.putShip(new Ship(Helper.getPos("d5"), 2, ShipOrientation.HORIZONTAL));

        while (!player.isLoose() && !computer.isLoose()) {
            player.displayBoard();
            player.enterFireCoordinate();
            // TODO: computer fire
        }

        if (player.isLoose()) {
            System.out.println("YOU LOOSE !");
        } else {
            System.out.println("YOU WIN !");
        }
    }
}
