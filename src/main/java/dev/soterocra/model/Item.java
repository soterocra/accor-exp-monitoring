package dev.soterocra.model;

import java.util.Objects;

public class Item {

    private String link;
    private String name;
    private String price;

    public Item() {
    }

    public Item(String link, String name, String price) {
        this.link = link;
        this.name = name;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getLink().equals(item.getLink()) && getName().equals(item.getName()) && getPrice().equals(item.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLink(), getName(), getPrice());
    }

    @Override
    public String toString() {
        return "Item{" +
                "link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
