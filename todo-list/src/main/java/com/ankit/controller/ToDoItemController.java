package com.ankit.controller;

import com.ankit.model.ToDoData;
import com.ankit.model.ToDoItem;
import com.ankit.service.ToDoItemService;
import com.ankit.util.AttributeNames;
import com.ankit.util.Mappings;
import com.ankit.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.Attribute;
import javax.swing.text.View;
import java.time.LocalDate;

@Slf4j
@Controller
public class ToDoItemController {

    // == fields ==
    private final ToDoItemService toDoItemService;

    // == constructor ==
    @Autowired
    public ToDoItemController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    // == model attributes ==
    @ModelAttribute
    public ToDoData toDoData() {
        return toDoItemService.getData();
    }

    // == controller methods ==
    @GetMapping(Mappings.ITEMS)
    public String items() {
        return ViewNames.ITEMS_LIST;
    }

    @GetMapping(Mappings.ADD_ITEM)
    public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {
        log.info("editing id = {}", id);
        ToDoItem toDoItem = toDoItemService.getItem(id);

        if (toDoItem == null) {
            toDoItem = new ToDoItem("", "", LocalDate.now());
        }

        model.addAttribute(AttributeNames.TODO_ITEM, toDoItem);
        return ViewNames.ADD_ITEM;
    }

    // to automatically get the ToDoItem parameter from the form, we need to add a @ModelAttribute annotation to the parameter
    // name of the parameter given in parentheses should match the name of the variable given in the form in jsp
    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) ToDoItem toDoItem) {
        log.info("ToDoItem from form = {}", toDoItem);

        if (toDoItem.getId() == 0) {
            toDoItemService.addItem(toDoItem);
        } else {
            toDoItemService.updateItem(toDoItem);
        }

        // after adding an item we want to redirect to a page showing all items
        return "redirect:/" + Mappings.ITEMS;
    }

    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id) {
        toDoItemService.removeItem(id);
        return "redirect:/" + Mappings.ITEMS;
    }

    @GetMapping(Mappings.VIEW_ITEM)
    public String viewItem(@RequestParam int id, Model model) {
        ToDoItem toDoItem = toDoItemService.getItem(id);
        model.addAttribute(AttributeNames.TODO_ITEM, toDoItem);
        return ViewNames.VIEW_ITEM;
    }
}
