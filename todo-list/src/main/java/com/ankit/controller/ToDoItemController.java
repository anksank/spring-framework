package com.ankit.controller;

import com.ankit.model.ToDoData;
import com.ankit.util.Mappings;
import com.ankit.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ToDoItemController {

    // == model attributes ==
    @ModelAttribute
    public ToDoData toDoData() {
        return new ToDoData();
    }

    // == controller methods ==
    @GetMapping(Mappings.ITEMS)
    public String items() {
        return ViewNames.ITEMS_LIST;
    }
}
