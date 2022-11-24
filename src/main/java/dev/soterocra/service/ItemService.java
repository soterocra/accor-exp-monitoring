package dev.soterocra.service;

import dev.soterocra.entity.ItemEntity;
import dev.soterocra.model.Item;
import dev.soterocra.model.RegionEnum;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ItemService {

    private final DynamoDbTable<ItemEntity> itemTable;

    public ItemService(DynamoDbEnhancedClient dynamo) {
        this.itemTable = dynamo.table(ItemEntity.TABLE_NAME, TableSchema.fromBean(ItemEntity.class));
    }

    public List<Item> findAll() {
        return itemTable.scan().items().stream()
                .map(itemEntity -> new Item(itemEntity.getLink(), itemEntity.getName(), itemEntity.getPrice(), RegionEnum.valueOf(Optional.ofNullable(itemEntity.getRegion()).orElse("BRAZIL"))))
                .toList();
    }

}
