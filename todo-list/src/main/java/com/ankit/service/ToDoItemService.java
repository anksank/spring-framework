package com.ankit.service;

import com.ankit.model.ToDoData;
import com.ankit.model.ToDoItem;

public interface ToDoItemService {

    void addItem(ToDoItem toAdd);

    void removeItem(int id);

    ToDoItem getItem(int id);

    void updateItem(ToDoItem toUpdate);

    ToDoData getData();
}
