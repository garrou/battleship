package com.battleship.utils;

import com.battleship.enums.ShipOrientation;
import com.battleship.models.Position;
import org.junit.Test;

import static com.battleship.utils.Helper.getOrientation;
import static com.battleship.utils.Helper.getPos;
import static org.junit.Assert.*;

public class HelperTest {

    @Test
    public void testGetPosWithValidUpperCase() {
        final String coordinate = "A5";
        final Position pos = getPos(coordinate);
        assertEquals(String.format("Expected %d, got %d", 0, pos.x()), 0, pos.x());
        assertEquals(String.format("Expected %d, got %d", 4, pos.y()), 4, pos.y());
    }

    @Test
    public void testGetPosWithValidLowerCase() {
        final String coordinate = "j10";
        final Position pos = getPos(coordinate);
        assertEquals(String.format("Expected %d, got %d", 9, pos.x()), 9, pos.x());
        assertEquals(String.format("Expected %d, got %d", 9, pos.y()), 9, pos.y());
    }

    @Test
    public void testGetOrientationWithVertical() {
        final String s = "V";
        final ShipOrientation orientation = getOrientation(s);
        assertEquals(String.format("Expected %s, got %s", ShipOrientation.VERTICAL, orientation),
                ShipOrientation.VERTICAL,
                orientation);
    }

    @Test
    public void testGetOrientationWithHorizontalLowerCase() {
        final String s = "h";
        final ShipOrientation orientation = getOrientation(s);
        assertEquals(String.format("Expected %s, got %s", ShipOrientation.HORIZONTAL, orientation),
                ShipOrientation.HORIZONTAL,
                orientation);
    }

    @Test
    public void testGetPosAndOrientation() {
        final String s = "D6 V";
        final Position pos = getPos(s);
        final ShipOrientation orientation = getOrientation(s);
        assertEquals(String.format("Expected %s, got %s", "(3, 5)", pos), "(3, 5)", pos.toString());
        assertEquals(String.format("Expected %s, got %s", ShipOrientation.VERTICAL, orientation),
                ShipOrientation.VERTICAL,
                orientation);
    }

    @Test
    public void testGetYesNoLowerCaseYes() {
        String choice = "y";
        boolean actual = Helper.getYesNo(choice);
        assertTrue(String.format("Expected %s, got %s", true, actual), actual);
    }

    @Test
    public void testGetYesNoLowerCaseNo() {
        String choice = "n";
        boolean actual = Helper.getYesNo(choice);
        assertFalse(String.format("Expected %s, got %s", false, actual), actual);
    }

    @Test
    public void testGetYesNoEmpty() {
        String choice = "";
        boolean actual = Helper.getYesNo(choice);
        assertFalse(String.format("Expected %s, got %s", false, actual), actual);
    }
}
