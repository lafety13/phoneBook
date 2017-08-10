package ua.lardi.phoneBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.lardi.phoneBook.service.ContactService;

/**
 * @author Vadim Kozak
 */
@Controller
public class MainController {
    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String mainPage(Model model) {




        return "home";
    }
}
