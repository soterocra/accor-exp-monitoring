package dev.soterocra.entity;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@DynamoDbBean
public class ItemEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -6824684687085279872L;

    public static final String TABLE_NAME = "accor-experiences";

    private String link;
    private String name;
    private String price;

    public ItemEntity() {
    }

    public ItemEntity(String link, String name, String price) {
        this.link = link;
        this.name = name;
        this.price = price;
    }

    @DynamoDbPartitionKey
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
        ItemEntity that = (ItemEntity) o;
        return getLink().equals(that.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLink());
    }
}
