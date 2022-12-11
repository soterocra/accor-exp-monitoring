package dev.soterocra.service;

import dev.soterocra.entity.ItemEntity;
import dev.soterocra.model.Item;
import dev.soterocra.model.RegionEnum;
import dev.soterocra.service.observer.ItemObserver;
import dev.soterocra.service.observer.ItemState;
import io.quarkus.logging.Log;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemService implements ItemObserver {

    private final DynamoDbTable<ItemEntity> itemTable;

    public ItemService(DynamoDbEnhancedClient dynamo, ItemState itemState) {
        this.itemTable = dynamo.table(ItemEntity.TABLE_NAME, TableSchema.fromBean(ItemEntity.class));
        itemState.addObserver(this);
    }

    public Set<Item> findAll() {
        return itemTable.scan().items().stream()
                .map(itemEntity -> new Item(itemEntity.getLink(), itemEntity.getName(), itemEntity.getPrice(), RegionEnum.valueOf(Optional.ofNullable(itemEntity.getRegion()).orElse("BRAZIL"))))
                .collect(Collectors.toSet());
    }

    public void newItem(Item item) {
        Log.info("Salvando item no banco.");
        itemTable.putItem(new ItemEntity(item.getLink(), item.getName(), item.getPrice(), item.getRegion().name()));
    }

    public void removedItem(Item item) {
        itemTable.deleteItem(new ItemEntity(item.getLink(), item.getName(), item.getPrice(), item.getRegion().name()));
    }
}
