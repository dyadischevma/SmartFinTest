package ru.dyadischevma.smartfintest.data;

import androidx.room.TypeConverter;

import java.util.UUID;

public class Converters {
    @TypeConverter
    public static String countryToString (Country country) {
        return country.name;
    }
    @TypeConverter
    public static Country stringToCountry (String countryName) {
        return Country.fromString(countryName);
    }
}