package ua.lardi.phoneBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.lardi.phoneBook.model.User;
import ua.lardi.phoneBook.service.UserService;

import java.util.List;

/**
 * @author Vadim Kozak
 */
@Controller
public class MainController {
//    @Autowired
//    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        List<User> listUsers = userService.findAll();
        model.addAttribute("userList", listUsers);

        return "index";
    }
}
