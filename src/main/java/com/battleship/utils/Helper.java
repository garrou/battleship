package com.battleship.utils;

import com.battleship.enums.ShipOrientation;
import com.battleship.models.Position;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static Position getPos(String coordinate) {
        Matcher matcherLetter = Pattern.compile("[a-jA-J]").matcher(coordinate);
        Matcher matcherNum = Pattern.compile("10|[1-9]").matcher(coordinate);
        String letter = matcherLetter.find() ? matcherLetter.group() : "";
        int number = matcherNum.find() ? Integer.parseInt(matcherNum.group()) : 0;
        int x = Arrays.asList(Constant.LETTERS).indexOf(letter.toUpperCase());
        int y = number <= Constant.BOARD_SIZE ? number - 1 : -1;

        return new Position(x, y);
    }

    public static ShipOrientation getOrientation(String coordinate) {
        Matcher matcherOrientation = Pattern.compile("[HVhv]").matcher(coordinate);
        String letter = matcherOrientation.find() ? matcherOrientation.group() : "";
        return letter.toUpperCase().compareTo(Constant.VERTICAL_LETTER) == 0
                ? ShipOrientation.VERTICAL
                : ShipOrientation.HORIZONTAL;
    }
}
