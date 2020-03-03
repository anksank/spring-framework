package com.ankit.model;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class ToDoData {
    private static int idValue = 0;

    private final List<ToDoItem> items = new ArrayList<>();

    public ToDoData() {

        // adding some dummy data
        addItem(new ToDoItem("first", "first details", LocalDate.now()));
        addItem(new ToDoItem("second", "second details", LocalDate.now()));
        addItem(new ToDoItem("third", "third details", LocalDate.now()));
    }

    public List<ToDoItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(@NonNull ToDoItem todoAdd) {
        todoAdd.setId(idValue);
        items.add(todoAdd);
        idValue++;
    }

    public void removeItem(int id) {
        ListIterator<ToDoItem> itemIterator = items.listIterator();

        while (itemIterator.hasNext()) {
            ToDoItem item = itemIterator.next();

            if (id == item.getId()) {
                itemIterator.remove();
                break;
            }
        }
    }

    public void updateItem(@NonNull ToDoItem updateItem) {
        ListIterator<ToDoItem> itemIterator = items.listIterator();

        while (itemIterator.hasNext()) {
            ToDoItem item = itemIterator.next();

            if (item.equals(updateItem)) {
                itemIterator.set(updateItem);
                break;
            }
        }
    }

    public ToDoItem getItem(int id) {
        for (ToDoItem item: items) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }
}
