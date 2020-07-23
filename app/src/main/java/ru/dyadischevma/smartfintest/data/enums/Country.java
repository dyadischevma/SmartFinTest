package ru.dyadischevma.smartfintest.data.enums;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public enum Country {
    ALL(0, "Все", Color.WHITE),
    RUSSIA(1, "Россия", Color.MAGENTA),
    BELORUS(2, "Белорусь", Color.GREEN);

    public final int position;
    public final String name;
    public final int color;

    Country(int position, String name, int color) {
        this.position = position;
        this.name = name;
        this.color = color;
    }

    public static Country fromString(String countryName) {
        for (Country c : Country.values()) {
            if (c.name.equals(countryName)) {
                return c;
            }
        }
        throw new NoSuchElementException("Country with name " + countryName + " not found!");
    }

    public static Country getCountryByPosition(int position) {
        for (Country c : Country.values()) {
            if (c.position == position) return c;
        }
        throw new NoSuchElementException("Country with position " + position + " not found!");
    }

    public static ArrayList<String> getCountryNames() {
        ArrayList<String> result = new ArrayList<>();
        for (Country c : Country.values()) {
            result.add(c.name);
        }
        return result;
    }
}