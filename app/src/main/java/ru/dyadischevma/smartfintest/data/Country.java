package ru.dyadischevma.smartfintest.data;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public enum Country {
    ALL(0, "Все"),
    RUSSIA(1, "Россия"),
    BELORUS(2, "Белорусь");

    public final int position;
    public final String name;

    Country(int position, String name) {
        this.position = position;
        this.name = name;
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
