package dev.soterocra.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Item {

    private String link;
    private String name;
    private String price;
    private RegionEnum region;

    public Item() {
    }

    public Item(String link, String name, String price, RegionEnum region) {
        this.link = link;
        this.name = name;
        this.price = price;
        this.region = region;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getLink().equals(item.getLink()) && getName().equals(item.getName()) && getPrice().equals(item.getPrice()) && getRegion() == item.getRegion();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLink(), getName(), getPrice(), getRegion());
    }

    @Override
    public String toString() {
        return "{\"Item\":{"
                + "\"link\":\"" + link + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"price\":\"" + price + "\""
                + ", \"region\":\"" + region + "\""
                + "}}";
    }
}
