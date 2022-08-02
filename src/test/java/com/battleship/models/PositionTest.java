package com.battleship.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void testIsValidPosWithValidPos() {
        Position pos = new Position(9, 4);
        boolean actual = pos.isValid();
        assertTrue(String.format("Expected %b, got %b", true, actual), actual);
    }

    @Test
    public void testIsValidPosWithInvalidX() {
        Position pos = new Position(-1, 6);
        boolean actual = pos.isValid();
        assertFalse(String.format("Expected %b, got %b", false, actual), actual);
    }

    @Test
    public void testIsValidPosWithInvalidY() {
        Position pos = new Position(4, 11);
        assertFalse(String.format("Expected %b, got %b", false, pos.isValid()), pos.isValid());
    }

    @Test
    public void testEqualsOk() {
        Position pos = new Position(1, 9);
        Position other = new Position(1, 9);
        assertEquals(String.format("Expected %b, got %b", true, pos.equals(other)), pos, other);
    }

    @Test
    public void testEqualsNok() {
        Position pos = new Position(1, 6);
        Position other = new Position(3, 9);
        assertNotEquals(String.format("Expected %b, got %b", false, pos.equals(other)), pos, other);
    }
}
