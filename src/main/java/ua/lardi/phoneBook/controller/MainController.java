package ua.lardi.phoneBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;
import ua.lardi.phoneBook.service.ContactService;
import ua.lardi.phoneBook.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        User currentUser = userService.findUserByLogin(principal.getName());
        List<Contact> userContacts = contactService.findAllContactsByUser(currentUser);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("contacts", userContacts);
        modelAndView.addObject("username", currentUser.getName());

        return new ModelAndView("home");
    }
}
