package com.battleship.models;

import com.battleship.enums.ShipOrientation;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShipTest {

    @Test
    public void testIsUnitHitOk() {
        Position pos = new Position(7, 6);
        Ship ship = new Ship(new Position(7, 3), 4, ShipOrientation.HORIZONTAL);
        Board board = new Board();

        ship.createUnit(pos);
        board.hit(pos);

        boolean actual = ship.isUnitHit(pos);
        assertTrue(String.format("Expected %b, got %b", true, actual), actual);
    }

    @Test
    public void testIsVerticalOk() {
        Ship ship = new Ship(new Position(7, 3), 4, ShipOrientation.VERTICAL);
        boolean actual = ship.isVertical();
        assertTrue(String.format("Expected %b, got %b", true, actual), actual);
    }

    @Test
    public void testIsVerticalNok() {
        Ship ship = new Ship(new Position(7, 3), 4, ShipOrientation.HORIZONTAL);
        boolean actual = ship.isVertical();
        assertFalse(String.format("Expected %b, got %b", false, actual), actual);
    }

    @Test
    public void testIsValidPosOk() {
        Ship ship = new Ship(new Position(6, 3), 4, ShipOrientation.HORIZONTAL);
        boolean actual = ship.isValidPos();
        assertTrue(String.format("Expected %b, got %b", true, actual), actual);
    }

    @Test
    public void testIsValidPosNokLength() {
        Ship ship = new Ship(new Position(7, 3), 4, ShipOrientation.HORIZONTAL);
        boolean actual = ship.isValidPos();
        assertFalse(String.format("Expected %b, got %b", false, actual), actual);
    }

    @Test
    public void testIsValidPosNok() {
        Ship ship = new Ship(new Position(7, 11), 4, ShipOrientation.HORIZONTAL);
        boolean actual = ship.isValidPos();
        assertFalse(String.format("Expected %b, got %b", false, actual), actual);
    }
}