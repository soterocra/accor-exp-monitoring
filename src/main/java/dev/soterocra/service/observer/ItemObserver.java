package dev.soterocra.service.observer;

import dev.soterocra.model.Item;

public interface ItemObserver {
    void newItem(Item item);
    void removedItem(Item item);
}
