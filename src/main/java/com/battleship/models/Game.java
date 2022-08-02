package com.battleship.models;

import com.battleship.utils.Helper;

import java.util.Scanner;

public class Game {

    private final Player player;

    private final Player computer;

    public Game(Player pOne, Player pTwo) {
        player = pOne;
        computer = pTwo;
    }

    public void launch() {

        askAddShipsAuto();
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

    private void askAddShipsAuto() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean isAuto = Helper.getYesNo(input);

        if (isAuto) {
            player.putShipsAutomatically();
        } else {
            player.putShipsManually();
        }
    }
}
