package com.battleship.models;

import com.battleship.enums.BoardState;
import com.battleship.enums.ShipOrientation;
import com.battleship.enums.ShipState;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class BoardTest {

    private static Board board;

    @Before
    public void Before() {
        board = new Board();
        int[][] grid = board.getGrid();

        Arrays.stream(grid)
                .forEach(arr -> Arrays.stream(arr)
                        .forEach(j -> assertEquals(BoardState.SEA.getValue(), j)));
    }

    @Test
    public void testPutShip() {
        Ship ship = new Ship(new Position(5, 1), 5, ShipOrientation.HORIZONTAL);
        board.putShip(ship);
        int[][] grid = board.getGrid();

        IntStream.range(5, 9)
                .forEach(i -> assertEquals(BoardState.BOAT.getValue(), grid[1][i]));
    }

    @Test
    public void testHit() {
        board.hit(new Position(8, 2));
        int[][] grid = board.getGrid();
        int expected = ShipState.MISS.getValue();
        int actual = grid[2][8];
        assertEquals(String.format("Expected %d, got %d", expected, actual), expected, actual);
    }

    @Test
    public void testIsValidPlacementOk() {
        Ship ship = new Ship(new Position(5, 1), 5, ShipOrientation.HORIZONTAL);
        boolean actual = board.isValidPlacement(ship);
        assertTrue(String.format("Expected %b, got %b", true, actual), actual);
    }

    @Test
    public void testIsValidPlacementNok() {
        Ship ship = new Ship(new Position(6, 1), 5, ShipOrientation.HORIZONTAL);
        boolean actual = board.isValidPlacement(ship);
        assertFalse(String.format("Expected %b, got %b", false, actual), actual);
    }

    @Test
    public void testWasNeverFireAtBefore() {
        Position position = new Position(6, 8);
        boolean actual = board.wasNeverFireAt(position);
        assertTrue(String.format("Expected %b, got %b", true, actual), actual);
    }

    @Test
    public void testWasNeverFireAtAfter() {
        Position position = new Position(6, 8);
        board.hit(position);
        boolean actual = board.wasNeverFireAt(position);
        assertFalse(String.format("Expected %b, got %b", false, actual), actual);
    }
}