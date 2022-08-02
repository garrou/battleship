package com.battleship.models;

public class Game {

    private final Player player;

    private final Player computer;

    public Game(Player pOne, Player pTwo) {
        player = pOne;
        computer = pTwo;
    }

    public void launch() {

        // TODO: ask to put manually or auto
        // player.putShipsManually();

        player.putShipsAutomatically();
        computer.putShipsAutomatically();

        while (!player.isLoose() && !computer.isLoose()) {
            player.displayBoard();
            player.enterFireCoordinate();
            computer.autoFire();
        }

        if (player.isLoose()) {
            System.out.println("YOU LOOSE !");
        } else {
            System.out.println("YOU WIN !");
        }
    }
}
