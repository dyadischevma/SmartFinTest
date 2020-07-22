package ru.dyadischevma.smartfintest.data.converters;

import androidx.room.TypeConverter;

import ru.dyadischevma.smartfintest.data.enums.Country;

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