package ru.dyadischevma.smartfintest.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

import ru.dyadischevma.smartfintest.data.enums.Country;

@Entity
public class Good {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private final String name;
    private final Country country;
    private final Long price;
    private final String link;

    public Good(long id, String name, Country country, Long price, String link) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.price = price;
        this.link = link;
    }

    @Ignore
    public Good(String name, Country country, Long price, String link) {
        this.name = name;
        this.country = country;
        this.price = price;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Long getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return id == good.id &&
                name.equals(good.name) &&
                country == good.country &&
                price.equals(good.price) &&
                Objects.equals(link, good.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, price, link);
    }
}