package dev.soterocra.entity;


import dev.soterocra.model.RegionEnum;
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
    private String region;

    public ItemEntity() {
    }

    public ItemEntity(String link, String name, String price, String region) {
        this.link = link;
        this.name = name;
        this.price = price;
        this.region = region;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
