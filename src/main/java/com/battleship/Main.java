package com.battleship;

import com.battleship.models.Board;
import com.battleship.models.Game;
import com.battleship.models.Player;

public class Main {

    public static void main(String[] args) {
        Board playerBoard = new Board();
        Board computerBoard = new Board();
        new Game(new Player(playerBoard, computerBoard), new Player(computerBoard, playerBoard)).launch();
    }
}
