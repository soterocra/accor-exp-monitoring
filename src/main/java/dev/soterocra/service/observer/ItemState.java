package dev.soterocra.service.observer;

import dev.soterocra.model.Item;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItemState {

    private List<ItemObserver> ItemObservers = new ArrayList<>();

    public void addObserver(ItemObserver itemObserver) {
        ItemObservers.add(itemObserver);
    }

    public void notifyNew(Item item) {
        ItemObservers.forEach(o -> o.newItem(item));
    }

    public void notifyRemoved(Item item) {
        ItemObservers.forEach(o -> o.removedItem(item));
    }

}
